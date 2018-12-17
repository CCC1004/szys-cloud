package cn.stylefeng.guns.modular.szys.logdict;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @Author: superC
 * @Date: 2018/12/3 11:57
 */
public class WrWiuBDict extends AbstractDictMap {
    @Override
    public void init() {
        put("guid", "主键ID");
        put("nsrsbh", "纳税人识别号");
        put("wiuNm", "取用水户全称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
