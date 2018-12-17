package cn.stylefeng.guns.modular.szys.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: superC
 * @Date: 2018/11/23 17:04
 */
public class WrWiuBWarpper extends BaseControllerWrapper{

    public WrWiuBWarpper(Map<String, Object> single) {
            super(single);
        }

    public WrWiuBWarpper(List<Map<String, Object>> multi) {
            super(multi);
        }

    public WrWiuBWarpper(Page<Map<String, Object>> page) {
            super(page);
        }

    public WrWiuBWarpper(PageResult<Map<String, Object>> pageResult) {
            super(pageResult);
        }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Object adlCd = map.get("adlCd");
        if(ToolUtil.isNotEmpty(adlCd)){
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(adlCd.toString());
            if(ToolUtil.isNotEmpty(wrAdB.getAdNm())){
                map.put("adlCdName", wrAdB.getAdNm());
            }
            //读取数据库
//            map.put("adlCdName", MyConstantFactory.me().getAddvName(adlCd.toString()));
        }
    }

}