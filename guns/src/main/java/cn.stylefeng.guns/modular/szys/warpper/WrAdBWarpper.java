package cn.stylefeng.guns.modular.szys.warpper;

import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.factory.IMyConstantFactory;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: superC
 * @Date: 2018/11/23 14:32
 */
public class WrAdBWarpper extends BaseControllerWrapper {
    
    public WrAdBWarpper(Map<String, Object> single) {
        super(single);
    }

    public WrAdBWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public WrAdBWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public WrAdBWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        String isEnable = map.get("isEnable").toString();
        if(ToolUtil.isNotEmpty(isEnable)){
            Map<String, String> isEnableMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("isEnable"));
            //根据code获取名称
            String name = isEnableMap.get(isEnable);
            //将code替换为其对应的名称
            map.put("isEnable",name);
        }
    }
}
