package cn.stylefeng.guns.modular.szys.logdict;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 业务日志字典--行政区划
 * @Author: superC
 * @Date: 2018/12/3 13:26
 */
public class WrAdBDict extends AbstractDictMap {
    @Override
    public void init() {
        put("adCd", "行政区划代码");
        put("adNm", "行政区划名称");
        put("adShnm", "行政区划简称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
