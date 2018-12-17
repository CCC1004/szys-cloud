package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.cache.CacheKey;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.model.OperationLog;
import cn.stylefeng.guns.modular.system.warpper.LogWarpper;
import cn.stylefeng.guns.modular.system.warpper.UserWarpper;
import cn.stylefeng.guns.modular.szys.logdict.WrAdBDict;
import cn.stylefeng.guns.modular.szys.logdict.WrWiuBDict;
import cn.stylefeng.guns.modular.szys.warpper.WrAdBWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.szys.service.IWrAdBService;

import javax.tools.Tool;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 行政区划管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-21 16:11:48
 */
@Controller
@RequestMapping("/wrAdB")
public class WrAdBController extends BaseController {

    private String PREFIX = "/szys/wrAdB/";

    @Autowired
    private IWrAdBService wrAdBService;

    /**
     * 跳转到行政区划管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wrAdB.html";
    }

    /**
     * 跳转到添加行政区划管理
     */
    @RequestMapping("/wrAdB_add")
    public String wrAdBAdd() {
        return PREFIX + "wrAdB_add.html";
    }

    /**
     * 跳转到修改行政区划管理
     */
    @RequestMapping("/wrAdB_update/{wrAdBId}")
    public String wrAdBUpdate(@PathVariable String wrAdBId, Model model) {
        WrAdB wrAdB = wrAdBService.selectById(wrAdBId);
        model.addAttribute("item",wrAdB);
        LogObjectHolder.me().set(wrAdB);
        return PREFIX + "wrAdB_edit.html";
    }

    /**
     * 获取行政区划管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String pageNumber,String size) {
        Page<WrAdB> page = new PageFactory<WrAdB>().defaultPage();
        List<Map<String, Object>> list = wrAdBService.selectWrAdBMapByParams(page,
                null, null, null,
                page.getOrderByField(),page.isAsc());
        page.setRecords(new WrAdBWarpper(list).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增行政区划管理
     */
    @BussinessLog(value = "新增行政区划", key = "adCd,adNm,adShnm", dict = WrAdBDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WrAdB wrAdB) {
        Date date = new Date();
        wrAdB.setTs(date);
        boolean b = wrAdBService.insert(wrAdB);
        if(b){
            CacheUtil.put(Cache.ADDV, CacheKey.ADDV+wrAdB.getAdCd(), wrAdB);
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除行政区划管理
     */
    @BussinessLog(value = "删除行政区划", key = "adCd", dict = WrAdBDict.class)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wrAdBId) {
        boolean b = wrAdBService.deleteById(wrAdBId);
        if(b){
            CacheUtil.remove(Cache.ADDV, CacheKey.ADDV+wrAdBId);
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改行政区划管理
     */
    @BussinessLog(value = "修改行政区划", key = "adCd,adNm,adShnm", dict = WrAdBDict.class)
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WrAdB wrAdB) {
        Date date = new Date();
        wrAdB.setTs(date);
        boolean b = wrAdBService.updateById(wrAdB);
        if(b){
            CacheUtil.remove(Cache.ADDV, CacheKey.ADDV+wrAdB.getAdCd());
        }
        return SUCCESS_TIP;
    }

    /**
     * 行政区划管理详情
     */
    @RequestMapping(value = "/detail/{wrAdBId}")
    @ResponseBody
    public Object detail(@PathVariable("wrAdBId") Integer wrAdBId) {
        return wrAdBService.selectById(wrAdBId);
    }
}
