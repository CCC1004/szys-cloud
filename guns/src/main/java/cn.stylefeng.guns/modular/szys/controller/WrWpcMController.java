package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.guns.modular.szys.logdict.WrPdoInfoDict;
import cn.stylefeng.guns.modular.szys.logdict.WrWpcMDict;
import cn.stylefeng.guns.modular.szys.service.IWrWiuBService;
import cn.stylefeng.guns.modular.szys.service.IWrWpcMDetailService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.guns.modular.szys.warpper.WrPdoInfoWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.szys.service.IWrWpcMService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 取水许可证控制器
 *
 * @author fengshuonan
 * @Date 2018-11-28 15:55:11
 */
@Controller
@RequestMapping("/wrWpcM")
public class WrWpcMController extends BaseController {

    private String PREFIX = "/szys/wrWpcM/";

    @Autowired
    private IWrWpcMService wrWpcMService;

    @Autowired
    private IWrWpcMDetailService wrWpcMDetailService;

    @Autowired
    private IWrWiuBService wrWiuBService;

    /**
     * 跳转到取水许可证首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wrWpcM.html";
    }

    /**
     * 跳转到添加取水许可证
     */
    @RequestMapping("/wrWpcM_add")
    public String wrWpcMAdd(Model model) {

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

        return PREFIX + "wrWpcM_add.html";
    }

    /**
     * 跳转到修改取水许可证
     */
    @RequestMapping("/wrWpcM_update/{wrWpcMId}")
    public String wrWpcMUpdate(@PathVariable String wrWpcMId, Model model) {
        WrWpcM wrWpcM = wrWpcMService.selectById(wrWpcMId);
        model.addAttribute("item",wrWpcM);
        LogObjectHolder.me().set(wrWpcM);

        //行政区划
        if(ToolUtil.isNotEmpty(wrWpcM.getAdCd())) {
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrWpcM.getAdCd());
            if (ToolUtil.isNotEmpty(wrAdB.getAdNm())) {
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //读取数据库
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrWpcM.getAdCd()));
        }

        /***************************************/
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

        return PREFIX + "wrWpcM_edit.html";
    }

    /**
     * 跳转到查看详情
     */
    @RequestMapping("/wrWpcM_detail/{wrWpcMId}")
    public String wrWpcMDetail(@PathVariable String wrWpcMId, Model model) {
        WrWpcM wrWpcM = wrWpcMService.selectById(wrWpcMId);
        model.addAttribute("item",wrWpcM);
        LogObjectHolder.me().set(wrWpcM);

        //行政区划
        if(ToolUtil.isNotEmpty(wrWpcM.getAdCd())) {
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrWpcM.getAdCd());
            if (ToolUtil.isNotEmpty(wrAdB.getAdNm())) {
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //读取数据库
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrWpcM.getAdCd()));
        }

        /***************************************/
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

        return PREFIX + "wrWpcM_edit_info.html";
    }

    /**
     * 获取取水许可证列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        Page<WrWpcMDetail> page = new PageFactory<WrWpcMDetail>().defaultPage();
        Wrapper<WrWpcMDetail> wrWpcMDetailWrapper = new EntityWrapper<WrWpcMDetail>();
        wrWpcMDetailWrapper.like("WRP_NM", condition);


        if (ShiroKit.isAdmin()){

        }
        //水行政管理部门
        else if(CommonUtils.isWaterManager()){

            String adid = CommonUtils.getAdid();

            String adidNextLike = CommonUtils.getAdidNextLike(adid);

            //获取该主管所在行政区划及其直接下级行政区划的取用水户列表
            wrWpcMDetailWrapper.eq("WIU_AD_CD", adid);
        }else if (CommonUtils.isWaterget()){
            String account = CommonUtils.getCurrentUserAccount();

            //普通用户
            wrWpcMDetailWrapper.eq("WIU_ACCOUNT", account);
        }
        else
        {
            return null;
        }

        Page<Map<String, Object>> mapPage = wrWpcMDetailService.selectMapsPage(page, wrWpcMDetailWrapper);
        //new WrPdoInfoWarpper(mapPage).wrap();
        return new PageInfoBT<>(mapPage);
    }

    /**
     * 新增取水许可证
     */
    @BussinessLog(value = "新增取水许可证", key = "wrpNm", dict = WrWpcMDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WrWpcM wrWpcM) {
        wrWpcM.setWpcCd(UUID.randomUUID().toString().replaceAll("-", ""));
        wrWpcM.setTs(new Date());
        wrWpcMService.insert(wrWpcM);
        return SUCCESS_TIP;
    }

    /**
     * 删除取水许可证
     */
    @BussinessLog(value = "删除取水许可证", key = "wrWpcMId", dict = WrWpcMDict.class)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String wrWpcMId) {
        wrWpcMService.deleteById(wrWpcMId);
        return SUCCESS_TIP;
    }

    /**
     * 修改取水许可证
     */
    @BussinessLog(value = "修改取水许可证", key = "wpcSn", dict = WrWpcMDict.class)
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WrWpcM wrWpcM) {
        wrWpcMService.updateById(wrWpcM);
        return SUCCESS_TIP;
    }

    /**
     * 取水许可证详情
     */
    @RequestMapping(value = "/detail/{wrWpcMId}")
    @ResponseBody
    public Object detail(@PathVariable("wrWpcMId") Integer wrWpcMId) {
        return wrWpcMService.selectById(wrWpcMId);
    }
}
