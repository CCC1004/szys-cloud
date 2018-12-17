package cn.stylefeng.guns.modular.szys.factory;

import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.cache.CacheKey;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.constant.state.MenuStatus;
import cn.stylefeng.guns.modular.system.dao.DictMapper;
import cn.stylefeng.guns.modular.system.dao.WrAdBMapper;
import cn.stylefeng.guns.modular.system.dao.WrWiuBMapper;
import cn.stylefeng.guns.modular.system.model.Dict;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sun.org.apache.regexp.internal.RE;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量的生产工厂
 * @Author: superC
 * @Date: 2018/11/23 14:42
 */
@Component(value = "myConstantFactory")
@DependsOn("springContextHolder")
public class MyConstantFactory extends ConstantFactory implements IMyConstantFactory {

    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);

    private WrAdBMapper wrAdBMapper = SpringContextHolder.getBean(WrAdBMapper.class);

    private WrWiuBMapper wrWiuBMapper = SpringContextHolder.getBean(WrWiuBMapper.class);

    private ResourcesProperties resourcesProperties = SpringContextHolder.getBean(ResourcesProperties.class);


    public static IMyConstantFactory me() {
        return SpringContextHolder.getBean("myConstantFactory");
    }

    /**
     * 获取行政区划
     * @param adlCd 行政区划id
     * @return
     */
    @Override
    @Cacheable(value = Cache.ADDV, key = "'" + CacheKey.ADDV + "'+#adlCd", unless="#result == null || #result == \"\"")
    public WrAdB getAddv(String adlCd) {
        return wrAdBMapper.selectById(adlCd);
    }

    /**
     * 获取行政区划名称
     * @param adId 行政区划id
     * @return
     */
    @Override
    public String getAddvName(String adId) {
        WrAdB wrAdB = wrAdBMapper.selectById(adId);
        if (ToolUtil.isNotEmpty(wrAdB) && ToolUtil.isNotEmpty(wrAdB.getAdNm())) {
            return wrAdB.getAdNm();
        }
        return "";
    }

    /**
     * 获取取水用户
     * @param wiuCd
     * @return
     */
    @Override
    public WrWiuB getWrWiuB(String wiuCd) {
        return wrWiuBMapper.selectById(wiuCd);
    }

    /**
     * 获取取水用户名称
     * @param wiuCd
     * @return
     */
    @Override
    public Object getWiuName(String wiuCd) {
        WrWiuB wrWiuB = wrWiuBMapper.selectById(wiuCd);
        if(ToolUtil.isNotEmpty(wrWiuB) && ToolUtil.isNotEmpty(wrWiuB.getWiuNm())){
            return wrWiuB.getWiuNm();
        }
        return "";
    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     * @param name 类型名称
     * @param val 数值
     * @return
     */
    @Override
    public String getDictNameByNmAndVal(String name, Integer val) {
        return getDictsByName(name,val);
    }

    /**
     * x
     * @param code
     * @return
     *  Cacheable参数解释：
     *      unless: unless="#result == null"，
     *      该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断。当方法返回空值时，就不会被缓存起来。
     */
    @Override
    @Cacheable(value = Cache.DICT, key = "#code", unless="#result == null || #result == \"\" || #result.size()>0 ")
    public Map<String, String> getDictSubMapByCode(String code) {
        Map<String, String> map = new HashMap<String, String>();
        Dict temp = new Dict();
        temp.setCode(code);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return map;
        } else {
            Wrapper<Dict> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("pid", dict.getId());
            List<Dict> dictList = dictMapper.selectList(wrapper);
            for(Dict d: dictList){
                map.put(d.getCode(),d.getName());
            }
            return map;
        }
    }


}
