package cn.stylefeng.guns.modular.szys.domain;


/**
 * 数据字典中编码（code）的枚举定义
 *      对应数据库中sys_dict中的code字段值
 * @Author: superC
 * @Date: 2018/11/30 16:31
 */
public enum DictEnum {

//    IS_HI_LEV: IS_HI_LEV       #  是否为高耗水行业
//    IS_VIMP: IS_VIMP         #是否重点监控取用水户
//    LAW_ATT: LAW_ATT         #取用水户性质
//    CERT_STA: CERT_STA         # 办证状态
//    is_enable: is_enable       #  行政区划是否启用
//    SOUR_TP: SOUR_TP          #水源类型
//    INT_USE: INT_USE          #取水用途
//    TRAD_TP: TRAD_TP          #取水行业
//    GWOA_TP: GWOA_TP          #地下水超采区类型
//    IS_PIPE: IS_PIPE          #管网覆盖
//    WRTR_TP: WRTR_TP          #税额等次

    IS_HI_LEV("isHiLev","IS_HI_LEV"),
    IS_VIMP("isVimp","IS_VIMP"),
    LAW_ATT("lawAtt","LAW_ATT"),
    CERT_STA("certSta","CERT_STA"),
    is_enable("isEnable","is_enable"),
    SOUR_TP("sourTp","SOUR_TP"),
    INT_USE("intUse","INT_USE"),
    TRAD_TP("tradTp","TRAD_TP"),
    GWOA_TP("gwoaTp","GWOA_TP"),
    IS_PIPE("isPipe","IS_PIPE"),
    WRTR_TP("wrtrTp","WRTR_TP"),
    STATE("state","sys_state")

    ;

    String code;
    String name;
    DictEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static String valueOfName(String code) {
        if (code == null) {
            return "";
        } else {
            for (DictEnum d : DictEnum.values()) {
                if (d.getCode() == code) {
                    return d.getName();
                }
            }
            return "";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
