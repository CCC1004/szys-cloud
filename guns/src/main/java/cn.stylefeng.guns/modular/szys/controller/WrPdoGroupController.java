package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.logdict.WrPdoGroupDict;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.guns.modular.szys.service.*;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.guns.modular.szys.warpper.WrPdoGroupWarpper;
import cn.stylefeng.guns.modular.szys.warpper.WrPdoInfoWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 水源分组控制器
 *
 * @author fengshuonan
 * @Date 2018-11-26 11:23:17
 */
@Controller
@RequestMapping("/wrPdoGroup")
public class WrPdoGroupController extends BaseController {

    private String PREFIX = "/szys/wrPdoGroup/";

    @Autowired
    private ResourcesProperties resourcesProperties;

    @Autowired
    private IWrPdoGroupService wrPdoGroupService;

    @Autowired
    private IWrPdoInfoService wrPdoInfoService;

    @Autowired
    private IWrPdoPdogroupRelService wrPdoPdogroupRelService;

    @Autowired
    private IWrPdoInfoDetailService wrPdoInfoDetailService;

    @Autowired
    private IWrWiuBService wrWiuBService;

    @Autowired
    private IWrWpcMDetailService wrWpcMDetailService;


    /**
     * 跳转到水源分组首页
     */
    @RequestMapping("")
    public String index(Model model) {

        Map<String, Map<String,String>> map = new HashMap<>();
        map.put ("sourTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("sourTp")));
        map.put ("intUse",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("intUse")));
        map.put ("tradTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("tradTp")));
        map.put ("gwoaTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("gwoaTp")));
        map.put ("isPipe",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("isPipe")));
        map.put ("state",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("state")));
        map.put ("wrtrTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("wrtrTp")));

        model.addAttribute("dict",map) ;

        return PREFIX + "wrPdoGroup.html";
    }

    /**
     * 跳转到添加水源分组
     */
    @RequestMapping("/wrPdoGroup_add")
    public String wrPdoGroupAdd() {
        return PREFIX + "wrPdoGroup_add.html";
    }

    /**
     * 跳转到修改水源分组
     */
    @RequestMapping("/wrPdoGroup_update/{wrPdoGroupId}")
    public String wrPdoGroupUpdate(@PathVariable String wrPdoGroupId, Model model) {
        WrPdoGroup wrPdoGroup = wrPdoGroupService.selectById(wrPdoGroupId);
        model.addAttribute("item",wrPdoGroup);
        LogObjectHolder.me().set(wrPdoGroup);
        return PREFIX + "wrPdoGroup_edit.html";
    }

    /**
     * 跳转到水源分组详情页
     */
    @RequestMapping("/wrPdoGroup_detail/{wrPdoGroupId}")
    public String wrPdoGroupDetail(@PathVariable String wrPdoGroupId, Model model) {
        WrPdoGroup wrPdoGroup = wrPdoGroupService.selectById(wrPdoGroupId);
        Map<String, Object> map = CommonUtils.object2Map(wrPdoGroup);
        new WrPdoGroupWarpper(map).wrap();
        model.addAttribute("item",map);
        LogObjectHolder.me().set(map);
        return PREFIX + "wrPdoGroup_detail.html";
    }

    /**
     * 获取水源分组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String groupNm,
                       @RequestParam(required = false) String wpcSn, 
                       @RequestParam(required = false) String sourTp,
                       @RequestParam(required = false) String intUse,
                       @RequestParam(required = false) String tradTp,
                       @RequestParam(required = false) String gwoaTp,
                       @RequestParam(required = false) String isPipe,
                       @RequestParam(required = false) String wrtrTp) {

//        List<Map<String, Object>> result = wrPdoGroupService.getWrPdoGroupsPage(page, groupNm, wpcSn, sourTp, intUse, tradTp, gwoaTp, isPipe, wrtrTp, page.getOrderByField(), page.isAsc());

        Page<WrPdoGroup> page = new PageFactory<WrPdoGroup>().defaultPage();
        Wrapper<WrPdoGroup> wrapper = new EntityWrapper<WrPdoGroup>();
        if(ToolUtil.isNotEmpty(groupNm)){
            wrapper.like("GROUP_NM", groupNm);
        }
        if(ToolUtil.isNotEmpty(wpcSn)){
            wrapper.like("WPC_SN", wpcSn);
        }
        if(ToolUtil.isNotEmpty(sourTp)){
            wrapper.eq("SOUR_TP", sourTp);
        }
        if(ToolUtil.isNotEmpty(intUse)){
            wrapper.eq("INT_USE", intUse);
        }
        if(ToolUtil.isNotEmpty(tradTp)){
            wrapper.eq("TRAD_TP", tradTp);
        }
        if(ToolUtil.isNotEmpty(gwoaTp)){
            wrapper.eq("GWOA_TP", gwoaTp);
        }
        if(ToolUtil.isNotEmpty(isPipe)){
            wrapper.eq("IS_PIPE", isPipe);
        }
        if(ToolUtil.isNotEmpty(wrtrTp)){
            wrapper.eq("WRTR_TP", wrtrTp);
        }

        Page<Map<String, Object>> mapPage = wrPdoGroupService.selectMapsPage(page, wrapper);
        new WrPdoGroupWarpper(mapPage).wrap();
        return new PageInfoBT<>(page);
    }

    /**
     * 新增水源分组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WrPdoGroup wrPdoGroup) {
        wrPdoGroupService.insert(wrPdoGroup);
        return SUCCESS_TIP;
    }

    /**
     * 删除水源分组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String wrPdoGroupId) {
        wrPdoGroupService.deleteById(wrPdoGroupId);
        return SUCCESS_TIP;
    }

    /**
     * 修改水源分组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WrPdoGroup wrPdoGroup) {
        wrPdoGroupService.updateById(wrPdoGroup);
        return SUCCESS_TIP;
    }

    /**
     * 水源分组详情
     */
    @RequestMapping(value = "/detail/{wrPdoGroupId}")
    @ResponseBody
    public Object detail(@PathVariable("wrPdoGroupId") String wrPdoGroupId) {
        return wrPdoGroupService.selectById(wrPdoGroupId);
    }


    /**
     * 跳转至进行分组
     */
    @RequestMapping("/wrPdoGroup_grouping")
    public String grouping(Model model){

        //获取取水户列表
        Wrapper<WrWiuB> wrWiuBWrapper = new EntityWrapper<WrWiuB>();
        String adid = CommonUtils.getAdid();
        wrWiuBWrapper.eq("ADL_CD", adid);
        List<WrWiuB> wiulist = wrWiuBService.selectList(wrWiuBWrapper) ;
        model.addAttribute("wiuList", wiulist);

        //获取取水许可证
        Wrapper<WrWpcMDetail> wpcWrapper = new EntityWrapper<WrWpcMDetail>();
        wpcWrapper.eq("WIU_AD_CD", adid);
        List<WrWpcMDetail> wpcList = wrWpcMDetailService.selectList(wpcWrapper);
        model.addAttribute("wpcList", wpcList);

        return  PREFIX + "wrPdoGroup_grouping.html";
    }

    /**
     *  对取水口进行分组时，根据所选类型，查询取水口展示列表
     * @param groupType 按照那种类型展示列表 分组类型（1：行政区划；2：取用水户；3：取水许可证）
     * @param opt
     * @return
     */
    @RequestMapping("/groupListByType")
    @ResponseBody
    public Object groupListByType(@RequestParam(required = false) String groupType,@RequestParam(required = false) String opt){

        //取水口视图列表 WrPdoInfoDetail
        Page<WrPdoInfoDetail> page = new PageFactory<WrPdoInfoDetail>().defaultPage();
        Wrapper<WrPdoInfoDetail> wrPdoInfoDetailWrapper = new EntityWrapper<WrPdoInfoDetail>();

        if(ToolUtil.isNotEmpty(groupType)){

            //获取当前用户行政区划id
            String adid = CommonUtils.getAdid();

            //根据行政区划，获取取水口列表
            if(groupType.equals(resourcesProperties.getAddvType())){
                //获取行政区划下的取用水户的取水口
                wrPdoInfoDetailWrapper.eq("WIU_AD_CD", adid);
            }
            //根据取用水户，获取取水口列表
            else if(groupType.equals(resourcesProperties.getWiubType())){
                //取用水户id
                wrPdoInfoDetailWrapper.eq("WIU_CD", opt);

            }
            //根据取水许可证，获取取水口列表
            else if(groupType.equals(resourcesProperties.getWpcType())){
                //无证
                if(opt.equals("0")){
                    //获取行政区划下的取用水户的取水口
                    wrPdoInfoDetailWrapper.eq("WIU_AD_CD", adid);
                    //取水许可证号为空
                    wrPdoInfoDetailWrapper.where("(WPC_SN IS NULL OR WPC_SN = '')");
                }else{
                    wrPdoInfoDetailWrapper.eq("WPC_CD", opt);
                }
            }

            Page<Map<String, Object>> mapPage = wrPdoInfoDetailService.selectMapsPage(page, wrPdoInfoDetailWrapper);
            new WrPdoInfoWarpper(mapPage).wrap();
            return new PageInfoBT<>(mapPage);
        }
        return null;
    }


    /**
     * 进行分组，分组提交(弃用)
     * @param groupType
     * @return
     */
    @BussinessLog(value = "水源分组", key = "groupType", dict = WrPdoGroupDict.class)
    @RequestMapping("/groupSubmit")
    @ResponseBody
    public Object groupSubmit(@RequestParam(required = true) String groupType){

        if(ToolUtil.isNotEmpty(groupType)){
            //分组提交，添加水源分组信息、水源分组与取水口关系表
            wrPdoGroupService.groupSubmit(groupType);
        }
        return SUCCESS_TIP;
    }


    /**
     * 进行分组，分组提交
     * @param groupType
     * @return
     */
    @BussinessLog(value = "水源分组", key = "groupSubmit2", dict = WrPdoGroupDict.class)
    @RequestMapping("/groupSubmit2")
    @ResponseBody
    public Object groupSubmit2(@RequestParam(required = true) String groupType,@RequestParam(required = false) String opt){

        if(ToolUtil.isNotEmpty(groupType)){
            //分组提交，添加水源分组信息、水源分组与取水口关系表
            wrPdoGroupService.groupSubmit2(groupType, opt);
        }
        return SUCCESS_TIP;
    }



}
