package cn.stylefeng.guns.modular.szys.logdict;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @Author: superC
 * @Date: 2018/12/3 13:37
 */
public class WrPdoGroupDict extends AbstractDictMap {
    @Override
    public void init() {
        put("groupType","分组类型（1：行政区划；2：取用水户；3：取水许可证）");
    }

    @Override
    protected void initBeWrapped() {

    }
}
