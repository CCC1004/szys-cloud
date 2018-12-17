package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.dictmap.MenuDict;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.guns.modular.szys.logdict.WrPdoInfoDict;
import cn.stylefeng.guns.modular.szys.logdict.WrWiuBDict;
import cn.stylefeng.guns.modular.szys.service.IWrPdoGroupService;
import cn.stylefeng.guns.modular.szys.service.IWrPdoInfoDetailService;
import cn.stylefeng.guns.modular.szys.service.IWrWiuBService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.guns.modular.szys.warpper.WrPdoGroupWarpper;
import cn.stylefeng.guns.modular.szys.warpper.WrPdoInfoWarpper;
import cn.stylefeng.guns.modular.szys.warpper.WrWiuBWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.session.RowBounds;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.szys.service.IWrPdoInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 取水口信息表控制器
 *
 * @author fengshuonan
 * @Date 2018-11-26 11:19:28
 */
@Controller
@RequestMapping("/wrPdoInfo")
public class WrPdoInfoController extends BaseController {

    private String PREFIX = "/szys/wrPdoInfo/";

	private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWrPdoInfoService wrPdoInfoService;

    @Autowired
    private IWrPdoInfoDetailService wrPdoInfoDetailService;

    @Autowired
    private IWrWiuBService wrWiuBService;

    /**
     * 跳转到取水口信息表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wrPdoInfo.html";
    }

    /**
     * 跳转到添加取水口信息表
     */
    @RequestMapping("/wrPdoInfo_add")
    public String wrPdoInfoAdd(Model model)
    {
        Map<String, Map<String,String>> map = new HashMap<>();
        map.put ("sourTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("sourTp")));
        map.put ("intUse",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("intUse")));
        map.put ("tradTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("tradTp")));
        map.put ("gwoaTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("gwoaTp")));
        map.put ("isPipe",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("isPipe")));
        map.put ("state",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("state")));
        map.put ("wrtrTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("wrtrTp")));

        model.addAttribute("dict",map);

		Wrapper<WrWiuB> wrWiuBWrapper = new EntityWrapper<WrWiuB>();



		//超级管理员
		if (ShiroKit.isAdmin()){

		}
		//水行政管理部门
		else if(CommonUtils.isWaterManager()){
			String adid = CommonUtils.getAdid();
			wrWiuBWrapper.eq("ADL_CD", adid);
		}
		//普通用户
		else if (CommonUtils.isWaterget()){
			return null;
		}
		else
		{
			return null;
		}

		List<WrWiuB> wrWiuBS = wrWiuBService.selectList(wrWiuBWrapper) ;
		model.addAttribute("wiubList", wrWiuBS);

        return PREFIX + "wrPdoInfo_add.html";
    }

    /**
     * 跳转到修改取水口信息表
     */
    @RequestMapping("/wrPdoInfo_update/{wrPdoInfoId}")
    public String wrPdoInfoUpdate(@PathVariable String wrPdoInfoId, Model model) {
        WrPdoInfo wrPdoInfo = wrPdoInfoService.selectById(wrPdoInfoId);
        model.addAttribute("item",wrPdoInfo);
        LogObjectHolder.me().set(wrPdoInfo);
        //行政区划
        if(ToolUtil.isNotEmpty(wrPdoInfo.getAdCd())) {
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrPdoInfo.getAdCd());
            if (ToolUtil.isNotEmpty(wrAdB.getAdNm())) {
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //读取数据库
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrPdoInfo.getAdCd()));
        }

        Map<String, Map<String,String>> map = new HashMap<>();
        map.put ("sourTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("sourTp")));
        map.put ("intUse",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("intUse")));
        map.put ("tradTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("tradTp")));
        map.put ("gwoaTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("gwoaTp")));
        map.put ("isPipe",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("isPipe")));
        map.put ("state",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("state")));
        map.put ("wrtrTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("wrtrTp")));

        model.addAttribute("dict",map) ;

		Wrapper<WrWiuB> wrWiuBWrapper = new EntityWrapper<WrWiuB>();



		//超级管理员
		if (ShiroKit.isAdmin()){

		}
		//水行政管理部门
		else if(CommonUtils.isWaterManager()){
			String adid = CommonUtils.getAdid();
			wrWiuBWrapper.eq("ADL_CD", adid);
		}
		//普通用户
		else if (CommonUtils.isWaterget()){
			return null;
		}
		else
		{
			return null;
		}

		List<WrWiuB> wrWiuBS = wrWiuBService.selectList(wrWiuBWrapper) ;
		model.addAttribute("wiubList", wrWiuBS);

        return PREFIX + "wrPdoInfo_edit.html";
    }

    /**
     * 跳转到修改取水口信息表
     */
    @RequestMapping("/wrPdoInfo_detail/{wrPdoInfoId}")
    public String wrPdoInfoUpdate2(@PathVariable String wrPdoInfoId, Model model) {
        System.out.println(((Map) CacheUtil.get(Cache.DICT,"sys_state")));
        WrPdoInfo wrPdoInfo = wrPdoInfoService.selectById(wrPdoInfoId);
        model.addAttribute("item",wrPdoInfo);
        LogObjectHolder.me().set(wrPdoInfo);

        //行政区划
        if(ToolUtil.isNotEmpty(wrPdoInfo.getAdCd())) {
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrPdoInfo.getAdCd());
            if (ToolUtil.isNotEmpty(wrAdB.getAdNm())) {
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //读取数据库
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrPdoInfo.getAdCd()));
        }

        Map<String, Map<String,String>> map = new HashMap<>();
        map.put ("sourTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("sourTp")));
        map.put ("intUse",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("intUse")));
        map.put ("tradTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("tradTp")));
        map.put ("gwoaTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("gwoaTp")));
        map.put ("isPipe",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("isPipe")));
        map.put ("state",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("state")));
        map.put ("wrtrTp",(Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName("wrtrTp")));

        model.addAttribute("dict",map) ;

		Wrapper<WrWiuB> wrWiuBWrapper = new EntityWrapper<WrWiuB>();



		//超级管理员
		if (ShiroKit.isAdmin()){

		}
		//水行政管理部门
		else if(CommonUtils.isWaterManager()){
			String adid = CommonUtils.getAdid();
			wrWiuBWrapper.eq("ADL_CD", adid);
		}
		//普通用户
		else if (CommonUtils.isWaterget()){
			return null;
		}
		else
		{
			return null;
		}

		List<WrWiuB> wrWiuBS = wrWiuBService.selectList(wrWiuBWrapper) ;
		model.addAttribute("wiubList", wrWiuBS);

        return PREFIX + "wrPdoInfo_edit_info.html";
    }

    /**
     * 获取取水口信息表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,HttpServletRequest request) {

        Page<WrPdoInfoDetail> page = new PageFactory<WrPdoInfoDetail>().defaultPage();
        Wrapper<WrPdoInfoDetail> wrPdoInfoWrapper = new EntityWrapper<WrPdoInfoDetail>();
        wrPdoInfoWrapper.like("INT_NM", condition);


        if (ShiroKit.isAdmin()){

        }
        //水行政管理部门
        else if(CommonUtils.isWaterManager()){

            String adid = CommonUtils.getAdid();

            String adidNextLike = CommonUtils.getAdidNextLike(adid);

            //获取该主管所在行政区划及其下级行政区划的取用水户列表
            wrPdoInfoWrapper.eq("WIU_AD_CD", adid);
        }else if (CommonUtils.isWaterget()){
            String account = CommonUtils.getCurrentUserAccount();

            //普通用户
			wrPdoInfoWrapper.eq("WIU_ACCOUNT", account);
        }
        else
        {
            return null;
        }

        Page<Map<String, Object>> mapPage = wrPdoInfoDetailService.selectMapsPage(page, wrPdoInfoWrapper);
        new WrPdoInfoWarpper(mapPage).wrap();
        return new PageInfoBT<>(mapPage);
    }

    /**
     * 新增取水口信息表
     */
	@BussinessLog(value = "新增取水口", key = "intNm", dict = WrPdoInfoDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WrPdoInfo wrPdoInfo) {
        wrPdoInfo.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        wrPdoInfo.setTs(new Date());
//        wrPdoInfoService.insert(wrPdoInfo);

        /*
            新增取水口，并更正水源分组
         */
        wrPdoInfoService.insertPdoAndCorrectGroup(wrPdoInfo);

        return SUCCESS_TIP;
    }

    /**
     * 删除取水口信息表
     */
	@BussinessLog(value = "删除取水口", key = "wrPdoInfoId", dict = WrPdoInfoDict.class)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String wrPdoInfoId) {
//        wrPdoInfoService.deleteById(wrPdoInfoId);

        /*
            删除取水口，并更正水源分组
         */
        wrPdoInfoService.deletePdoAndCorrectGroup(wrPdoInfoId);

        return SUCCESS_TIP;
    }

    /**
     * 修改取水口信息表
     */
	@BussinessLog(value = "修改取水口", key = "wrPdoInfo", dict = WrPdoInfoDict.class)
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WrPdoInfo wrPdoInfo) {
        wrPdoInfoService.updateById(wrPdoInfo);

         /*
            修改取水口，并更正水源分组
         */
         wrPdoInfoService.updatePdoAndCorrectGroup(wrPdoInfo);

        return SUCCESS_TIP;
    }


    /**
     * 取水口信息表详情
     */
    @RequestMapping(value = "/detail/{wrPdoInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("wrPdoInfoId") String wrPdoInfoId)
    {
        return wrPdoInfoService.selectById(wrPdoInfoId);
    }

}
