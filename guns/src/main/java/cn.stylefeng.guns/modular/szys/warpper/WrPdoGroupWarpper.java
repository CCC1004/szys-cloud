package cn.stylefeng.guns.modular.szys.warpper;

import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * 水源分组包装类
 * @Author: superC
 * @Date: 2018/11/26 14:25
 */
public class WrPdoGroupWarpper extends BaseControllerWrapper {
    public WrPdoGroupWarpper(Map<String, Object> single) {
        super(single);
    }

    public WrPdoGroupWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public WrPdoGroupWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public WrPdoGroupWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

        String wiuCd = (String) map.get("wiuCd");//取水用户编码
//        String wpcSn = (String) map.get("wpcSn");//取水许可证号
        String sourTp = (String) map.get("sourTp");//水源类型
        String intUse = (String) map.get("intUse");//取水用途
        String tradTp = (String) map.get("tradTp");//取水行业
        String gwoaTp = (String) map.get("gwoaTp");//地下水超采区类型
        String isPipe = (String) map.get("isPipe");//管网覆盖
        String wrtrTp = (String) map.get("wrtrTp");//税额等次
        String adCd = (String) map.get("adCd");//行政区划

        //取水用户名称
        if(ToolUtil.isNotEmpty(wiuCd)){
            map.put("wiuNm", MyConstantFactory.me().getWiuName(wiuCd));
        }

        //行政区划
        if(ToolUtil.isNotEmpty(adCd)){
            WrAdB addv = MyConstantFactory.me().getAddv(adCd);
            if(ToolUtil.isNotEmpty(addv) && ToolUtil.isNotEmpty(addv.getAdNm())){
                map.put("adNm", addv.getAdNm());
            }
        }

        //六要素
        if(ToolUtil.isNotEmpty(sourTp)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("sourTp"));
            //根据code获取名称
            String name = dictMap.get(sourTp);
            //将code替换为其对应的名称
            map.put("sourTp",name);
        }
        if(ToolUtil.isNotEmpty(intUse)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("intUse"));
            //根据code获取名称
            String name = dictMap.get(intUse);
            //将code替换为其对应的名称
            map.put("intUse",name);
        }
        if (ToolUtil.isNotEmpty(tradTp)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("tradTp"));
            //根据code获取名称
            String name = dictMap.get(tradTp);
            //将code替换为其对应的名称
            map.put("tradTp",name);
        }
        if (ToolUtil.isNotEmpty(gwoaTp)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("gwoaTp"));
            //根据code获取名称
            String name = dictMap.get(gwoaTp);
            //将code替换为其对应的名称
            map.put("gwoaTp",name);
        }
        if(ToolUtil.isNotEmpty(isPipe)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("isPipe"));
            //根据code获取名称
            String name = dictMap.get(isPipe);
            //将code替换为其对应的名称
            map.put("isPipe",name);
        }
        if(ToolUtil.isNotEmpty(wrtrTp)){
            Map<String, String> dictMap = MyConstantFactory.me().getDictSubMapByCode(DictEnum.valueOfName("wrtrTp"));
            //根据code获取名称
            String name = dictMap.get(wrtrTp);
            //将code替换为其对应的名称
            map.put("wrtrTp",name);
        }

    }

}
