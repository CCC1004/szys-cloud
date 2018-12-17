package cn.stylefeng.guns.modular.szys.controller;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.service.IDeptService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 公共接口
 * @Author: superC
 * @Date: 2018/11/22 15:10
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private IDeptService deptService;

    /**
     * 获取行政区划的tree列表
     */
    @RequestMapping(value = "/adTree")
    @ResponseBody
    public List<ZTreeNode> adTree() {
        //获取当前用户行政区划id
        String adid = CommonUtils.getAdid();
        //根据行政区划id，获取用于模糊查询该行政区划及其子集的编码片段
        String adidLike = CommonUtils.getAdidLike(adid);

        List<ZTreeNode> tree = this.deptService.adTreeByAdidLike(adidLike);

//        List<ZTreeNode> tree = this.deptService.adTree();

        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     *
     */


}
