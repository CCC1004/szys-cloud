package cn.stylefeng.guns.modular.szys.factory;

import cn.stylefeng.guns.core.common.constant.factory.IConstantFactory;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.system.model.WrWiuB;

import java.util.Map;

/**
 *  常量生产工厂的接口
 * @Author: superC
 * @Date: 2018/11/23 14:39
 */
public interface IMyConstantFactory extends IConstantFactory {

    /**
     * 获取行政区划
     */
    WrAdB getAddv(String adlCd);

    /**
     * 获取行政区划名称
     */
    String getAddvName(String adId);

    /**
     * 获取取水用户
     * @param wiuCd
     * @return
     */
    WrWiuB getWrWiuB(String wiuCd);

    /**
     * 获取取水用户名称
     * @param wiuCd
     * @return
     */
    Object getWiuName(String wiuCd);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     * @param name 字典名称
     * @param val 字典中的数值
     * @return
     */
    String getDictNameByNmAndVal(String name, Integer val);


    /**
     * 根据字典父code，获取其子集Map
     */
    Map<String,String> getDictSubMapByCode(String code);


}
