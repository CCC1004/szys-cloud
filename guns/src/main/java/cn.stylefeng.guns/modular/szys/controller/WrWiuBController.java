package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.cache.CacheKey;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.model.Dept;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.guns.modular.system.service.IDeptService;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.szys.factory.MyConstantFactory;
import cn.stylefeng.guns.modular.szys.logdict.WrWiuBDict;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.guns.modular.szys.service.IWrAdBService;
import cn.stylefeng.guns.modular.szys.service.IWrWiuBService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.guns.modular.szys.warpper.WrWiuBWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 取用水户管理控制器
 *
 * @author superC
 * @Date 2018-11-21 10:28:41
 */
@Controller
@RequestMapping("/wrWiuB")
public class WrWiuBController extends BaseController {

    private String PREFIX = "/szys/wrWiuB/";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWrWiuBService wrWiuBService;

    @Autowired
    private ResourcesProperties resourcesProperties;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IWrAdBService wrAdBService;

    /**
     * 跳转到取用水户管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wrWiuB.html";
    }

    /**
     * 跳转到添加取用水户管理
     */
    @RequestMapping("/wrWiuB_add")
    public String wrWiuBAdd(Model model) {
        //获取当前用户所在行政区划
        String adid = CommonUtils.getAdid();
        WrAdB wrAdB = wrAdBService.selectById(adid);
        model.addAttribute("item",wrAdB);

        return PREFIX + "wrWiuB_add.html";
    }

    /**
     * 跳转到修改取用水户管理
     */
    @RequestMapping("/wrWiuB_update/{wrWiuBId}")
    public String wrWiuBUpdate(@PathVariable String wrWiuBId, Model model) {
        WrWiuB wrWiuB = wrWiuBService.selectById(wrWiuBId);
        model.addAttribute("item",wrWiuB);
        //行政区划
        if(ToolUtil.isNotEmpty(wrWiuB.getAdlCd())){
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrWiuB.getAdlCd());
            if(ToolUtil.isNotEmpty(wrAdB.getAdNm())){
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //不使用缓存，从数据库读取
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrWiuB.getAdlCd()));
        }
        LogObjectHolder.me().set(wrWiuB);
        return PREFIX + "wrWiuB_edit.html";
    }

    /**
     * 获取取用水户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        //超管
        if(ShiroKit.isAdmin()){
            List<Map<String, Object>> list =
                    wrWiuBService.selectWrWiuBMapByParams(null, null, null, null, null);
            return super.warpObject(new WrWiuBWarpper(list));
        }
        //水行政管理部门
        else if(CommonUtils.isWaterManager()){

            String adid = CommonUtils.getAdid();

            //根据行政区划id，获取用于模糊查询该行政区划及其子集的编码片段
//            String adidLike = CommonUtils.getAdidLike(adid);

            //获取该主管所在行政区划及其下级行政区划的取用水户列表
            List<Map<String, Object>> list =
                    wrWiuBService.selectWrWiuBMapByParams(null, null, null, null, adid);
            return super.warpObject(new WrWiuBWarpper(list));
        }else{
            return null;
        }

    }

    /**
     * 新增取用水户管理
     */
    @BussinessLog(value = "新增取用水户", key = "guid,nsrsbh,wiuNm", dict = WrWiuBDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WrWiuB wrWiuB) {
        String guid = CommonUtils.getGuid();
        wrWiuB.setUuid(guid);
        Date date = new Date();
        wrWiuB.setTs(date);
        boolean insertOr = wrWiuBService.insert(wrWiuB);
        /**
         * 添加用户
         */
        if(insertOr) {
            User user = new User();

            //纳税人识别号
            String nsrsbh = wrWiuB.getNsrsbh();
            //设置用户登录名
            user.setAccount(nsrsbh);

            //取用水户名称
            String name = wrWiuB.getWiuNm();
            user.setName(name);

            //行政区划id
            String adlCd = wrWiuB.getAdlCd();
            // 根据行政区划，获取部门id
//            deptService.selectOne(adlCd);
//            user.setDeptid();

            //获取默认密码
            String dePassword = resourcesProperties.getDePassword();
            // 获取密码盐
            user.setSalt(ShiroKit.getRandomSalt(5));
            //设置默认密码
            user.setPassword(ShiroKit.md5(dePassword, user.getSalt()));

            //获取默认用户类型
            String deType = resourcesProperties.getDeType();
            //设置用户类型
            user.setType(deType);

            //获取默认用户角色
            String roleid = resourcesProperties.getDeRole();
            //设置角色为取用水户
            user.setRoleid(roleid);

            //设置状态为启用（1）
            user.setStatus(1);

            //设置创建时间
            Date createTime = new Date();
            user.setCreatetime(createTime);

            boolean insertUserOr = userService.insert(user);
            if(insertUserOr) {
                log.info("添加用户成功！");
            }

        }
        return SUCCESS_TIP;
    }

    /**
     * 删除取用水户管理
     */
    @BussinessLog(value = "删除取用水户", key = "guid", dict = WrWiuBDict.class)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String wrWiuBId) {
        wrWiuBService.deleteById(wrWiuBId);
        return SUCCESS_TIP;
    }

    /**
     * 修改取用水户管理
     */
    @BussinessLog(value = "修改取用水户", key = "guid,nsrsbh,wiuNm", dict = WrWiuBDict.class)
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WrWiuB wrWiuB) {
        Date date = new Date();
        wrWiuB.setTs(date);
        wrWiuBService.updateById(wrWiuB);
        return SUCCESS_TIP;
    }

    /**
     * 取用水户管理详情
     */
    @RequestMapping(value = "/detail/{wrWiuBId}")
    @ResponseBody
    public Object detail(@PathVariable("wrWiuBId") String wrWiuBId) {
        return wrWiuBService.selectById(wrWiuBId);
    }



    /***************************取用水户查看页面信息********************************/

    /**
     * 跳转到添加取用水户信息
     */
    @RequestMapping("/wrWiuB_info_user")
    public String wrWiuBInfoUser() {
        return PREFIX + "wrWiuBUser/wrWiuB_info_user.html";
    }

    /**
     * 查看取用水户信息
     */
    @GetMapping("/getWrWiuBInfo")
    public Object getWrWiuBInfo(Model model){
        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();
        //账户
        String account = user.account;
        //根据当前用户登录名，获取取用水信息
        WrWiuB wrWiuB = wrWiuBService.selectByAccount(account);
        model.addAttribute("item",wrWiuB);
        //行政区划
        if(ToolUtil.isNotEmpty(wrWiuB)){
            //使用ehcache
            WrAdB wrAdB = MyConstantFactory.me().getAddv(wrWiuB.getAdlCd());
            if(ToolUtil.isNotEmpty(wrAdB.getAdNm())){
                model.addAttribute("adNm", wrAdB.getAdNm());
            }
            //读取数据库
//            model.addAttribute("adNm", MyConstantFactory.me().getAddvName(wrWiuB.getAdlCd()));
        }
        LogObjectHolder.me().set(wrWiuB);
        return PREFIX + "wrWiuBUser/wrWiuB_info_user.html";
    }
}
