package cn.stylefeng.guns.modular.szys.service.impl;

import cn.stylefeng.guns.modular.system.dao.WrPdoGroupMapper;
import cn.stylefeng.guns.modular.system.dao.WrPdoInfoMapper;
import cn.stylefeng.guns.modular.system.dao.WrPdoPdogroupRelMapper;
import cn.stylefeng.guns.modular.system.model.WrPdoGroup;
import cn.stylefeng.guns.modular.system.model.WrPdoInfo;
import cn.stylefeng.guns.modular.system.model.WrPdoPdogroupRel;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.guns.modular.szys.service.IWrPdoGroupService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水源分组表 服务实现类
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
@Service
public class WrPdoGroupServiceImpl extends ServiceImpl<WrPdoGroupMapper, WrPdoGroup> implements IWrPdoGroupService {


    @Autowired
    private ResourcesProperties resourcesProperties;

    @Autowired
    private WrPdoInfoMapper wrPdoInfoMapper;

    @Autowired
    private WrPdoPdogroupRelMapper wrPdoPdogroupRelMapper;

    /**
     * @param page
     * @param groupNm   取水口名称
     * @param wpcSn     取水许可证号
     * @param sourTp      水源类型
     * @param intUse      取水用途
     * @param tradTp      取水行业
     * @param gwoaTp      地下水超采区类型
     * @param isPipe      管网覆盖
     * @param wrtrTp      税额等次
     * @param orderByField
     * @param asc
     * @return
     */
    @Override
    public List<Map<String, Object>> getWrPdoGroupsPage(Page<WrPdoGroup> page, String groupNm, String wpcSn, String sourTp, String intUse, String tradTp, String gwoaTp, String isPipe, String wrtrTp, String orderByField, boolean asc) {
        return this.baseMapper.getWrPdoGroupsPage(page,groupNm,wpcSn,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,orderByField,asc);
    }

    /************************水源分组   公共方法:  ***************************/

    /**
     * 分组提交，添加水源分组信息、水源分组与取水口关系表
     * @param groupType   分组类型（1：行政区划；2：取用水户；3：取水许可证）
     */
    @Override
    public void groupSubmit(String groupType) {
        List<Map<String, Object>> groupList = null;
        List<WrPdoInfo> pdoList = null;

        //获取当前用户行政区划id
        String adid = CommonUtils.getAdid();
        //根据行政区划id，获取用于模糊查询该行政区划及其子集的编码片段
        String adidLike = CommonUtils.getAdidLike(adid);

        if(resourcesProperties.getAddvType().equals(groupType)){    //根据行政区划，获取取水口列表

            groupList = wrPdoInfoMapper.getGroupMapByAdidLike(adidLike);

            for (Map<String, Object> groupInfo : groupList) {
                //根据分组信息，获取其取水口信息列表
                pdoList = getPdoListByType(adidLike,groupInfo,groupType);

                //添加水源分组信息，及水源分组与取水口的关系表
                addWrPdoGroupInfo(groupInfo,pdoList);
            }

        }else if(resourcesProperties.getWiubType().equals(groupType)){  //根据取用水户，获取取水口列表
            //根据当前用户登录名，获取取用水户的信息
//            WrWiuB wrWiuB = CommonUtils.getWrWiuBInfoByUsername();
//            if(ToolUtil.isNotEmpty(wrWiuB) && ToolUtil.isNotEmpty(wrWiuB.getUuid())){
//                String uuid = wrWiuB.getUuid();
            if (ToolUtil.isNotEmpty(adidLike)){
                //根据取用水户编码，获取取水口列表,并进行分组
                groupList = wrPdoInfoMapper.getGroupMapByUuid(adidLike);

                for (Map<String, Object> groupInfo : groupList) {
                    //根据分组信息，获取其取水口信息列表
                    pdoList = getPdoListByType(adidLike,groupInfo,groupType);

                    //添加水源分组信息，及水源分组与取水口的关系表
                    addWrPdoGroupInfo(groupInfo,pdoList);
                }

            }
        }else if(resourcesProperties.getWpcType().equals(groupType)){    //根据取水许可证，获取取水口列表
            if(ToolUtil.isNotEmpty(adidLike)) {
                groupList = wrPdoInfoMapper.getGroupMapByWpcM(adidLike);

                for (Map<String, Object> groupInfo : groupList) {
                    //根据分组信息，获取其取水口信息列表
                    pdoList = getPdoListByType(adidLike,groupInfo,groupType);

                    //添加水源分组信息，及水源分组与取水口的关系表
                    addWrPdoGroupInfo(groupInfo,pdoList);
                }

            }
        }

    }

    /**
     * 分组提交，添加水源分组信息、水源分组与取水口关系表
     * @param groupType   分组类型（1：行政区划；2：取用水户；3：取水许可证）
     * @param opt 所选取用水户编号或者取水许可证号
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void groupSubmit2(String groupType, String opt) {
        List<Map<String, Object>> groupList = null;
        List<WrPdoInfo> pdoList = null;

        //获取当前用户行政区划id
        String adid = CommonUtils.getAdid();
        if(resourcesProperties.getAddvType().equals(groupType)){    //根据行政区划，获取取水口列表
            groupList = wrPdoInfoMapper.getGroupMapByAdid(adid);

            for (Map<String, Object> groupInfo : groupList) {
                //根据分组信息，获取其取水口信息列表
                pdoList = getPdoListByType(adid,groupInfo,groupType);

                //添加水源分组信息，及水源分组与取水口的关系表
                addWrPdoGroupInfo(groupInfo,pdoList);
            }

        }else if(resourcesProperties.getWiubType().equals(groupType)){  //根据取用水户，获取取水口列表
            //根据当前用户登录名，获取取用水户的信息
            if (ToolUtil.isNotEmpty(opt)){
                //根据取用水户编码，获取取水口列表,并进行分组
                groupList = wrPdoInfoMapper.getGroupMapByWiuCd(opt);

                for (Map<String, Object> groupInfo : groupList) {
                    //根据分组信息，获取其取水口信息列表
                    pdoList = getPdoListByType(opt,groupInfo,groupType);

                    //添加水源分组信息，及水源分组与取水口的关系表
                    addWrPdoGroupInfo(groupInfo,pdoList);
                }

            }else{
                System.out.println("请选择取用水户");
            }
        }else if(resourcesProperties.getWpcType().equals(groupType)){    //根据取水许可证，获取取水口列表
            if(ToolUtil.isNotEmpty(opt)) {
                groupList = wrPdoInfoMapper.getGroupMapByWpcCd(opt);

                for (Map<String, Object> groupInfo : groupList) {
                    //根据分组信息，获取其取水口信息列表
                    pdoList = getPdoListByType(opt,groupInfo,groupType);

                    //添加水源分组信息，及水源分组与取水口的关系表
                    addWrPdoGroupInfo(groupInfo,pdoList);
                }

            }else{
                System.out.println("请选择取水许可证");
            }
        }

    }


    /**
     * 根据分组信息，获取其取水口信息列表
     * @param filterArg   查询分组过滤条件参数
     * @param groupInfo
     * @param groupType     分组类型（1：行政区划；2：取用水户；3：取水许可证）
     * @return
     */
    private List<WrPdoInfo> getPdoListByType(String filterArg, Map<String, Object> groupInfo, String groupType){

        String isWpcNull = "";
        String sourTp = "";
        String intUse = "";
        String tradTp = "";
        String gwoaTp = "";
        String isPipe = "";
        String wrtrTp = "";
        String wpcCd = "";
        String wiuCd = "";
        String acCd = "";
        if (ToolUtil.isNotEmpty(groupInfo.get("isWpcNull"))){
            isWpcNull = groupInfo.get("isWpcNull").toString();  //许可证是否为空
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("sourTp"))){
            sourTp = groupInfo.get("sourTp").toString();//水源类型
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("intUse"))){
            intUse = groupInfo.get("intUse").toString();//取水用途
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("tradTp"))){
            tradTp = groupInfo.get("tradTp").toString();//取水行业
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("gwoaTp"))){
            gwoaTp = groupInfo.get("gwoaTp").toString();//地下水超采区类型
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("isPipe"))){
            isPipe = groupInfo.get("isPipe").toString();//管网覆盖
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("wrtrTp"))){
            wrtrTp = groupInfo.get("wrtrTp").toString();//税额等次
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("wpcCd"))){
            wpcCd = groupInfo.get("wpcCd").toString();//许可证号
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("wiuCd"))){
            wiuCd = groupInfo.get("wiuCd").toString();//取用水户
        }
        if (ToolUtil.isNotEmpty(groupInfo.get("acCd"))){
            acCd = groupInfo.get("acCd").toString();//行政区划
        }


        //根据行政区划，
        if(groupType.equals(resourcesProperties.getAddvType())){
//            return wrPdoInfoMapper.getPdoListByGroupInfoAndAdidLike(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
            return wrPdoInfoMapper.getPdoListByGroupInfoAndAdid(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
        }
        //根据取用水户，
        else if(groupType.equals(resourcesProperties.getWiubType())){
//            return wrPdoInfoMapper.getPdoListByGroupInfo(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
            return wrPdoInfoMapper.getPdoListByGroupInfoAndWiuCd(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
        }
        //根据取水许可证，获取取水口列表
        else if(groupType.equals(resourcesProperties.getWpcType())){
//            return wrPdoInfoMapper.getPdoListByGroupInfoAndWpcM(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
            return wrPdoInfoMapper.getPdoListByGroupInfoAndWpcCd(filterArg,isWpcNull,sourTp,intUse,tradTp,gwoaTp,isPipe,wrtrTp,wpcCd,wiuCd,acCd);
        }else{
            return null;
        }
    }

    /**
     * 添加水源分组信息，及关系表
     * @param groupInfo
     * @param pdoList
     */
    private void addWrPdoGroupInfo(Map<String, Object> groupInfo, List<WrPdoInfo> pdoList) {
        /*
            添加水源分组
         */
        WrPdoGroup wrPdoGroup = getWrPdoGroupInfo(groupInfo);
        String groupUuid = CommonUtils.getGuid();//水源分组主键编码
        wrPdoGroup.setUuid(groupUuid);
        String groupNm = "";//水源分组名称
        for (WrPdoInfo wrPdoInfo : pdoList) {
            String pdoInfoUuid = wrPdoInfo.getUuid();//取水口编码
            /*
                添加关系表
             */
            WrPdoPdogroupRel rel = new WrPdoPdogroupRel();
            rel.setPdoGroupId(groupUuid);
            rel.setPdoId(pdoInfoUuid);
            rel.setStatus(resourcesProperties.getGroupRelUse());
            rel.setTs(CommonUtils.getCreateTime());
            wrPdoPdogroupRelMapper.insert(rel);

            String intNm = wrPdoInfo.getIntNm();//取水口名称
            groupNm += intNm + ";";

        }
        groupNm = groupNm.substring(0,groupNm.length()-1);//去掉最后一个分号（;）
        wrPdoGroup.setGroupNm(groupNm);

        this.baseMapper.insert(wrPdoGroup);

    }

    /**
     * 获取水源信息
     */
    private WrPdoGroup getWrPdoGroupInfo(Map<String, Object> groupInfo){
        WrPdoGroup wrPdoGroup = new WrPdoGroup();
        //取水许可证
//        if(ToolUtil.isNotEmpty(groupInfo.get("wpcIsNull"))){
//        }
        if(ToolUtil.isNotEmpty(groupInfo.get("wpcCd"))){
            String wpcCd = (String) groupInfo.get("wpcCd");//取水许可证id
            wrPdoGroup.setWpcCd(wpcCd);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("wiuCd"))){
            String wiuCd = (String) groupInfo.get("wiuCd");//取用水户id
            wrPdoGroup.setWiuCd(wiuCd);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("adCd"))){
            String adCd = (String) groupInfo.get("adCd");//行政区划id
            wrPdoGroup.setAdCd(adCd);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("sourTp"))){
            String sourTp = (String) groupInfo.get("sourTp");//水源类型
            wrPdoGroup.setSourTp(sourTp);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("intUse"))){
            String intUse = (String) groupInfo.get("intUse");//取水用途
            wrPdoGroup.setIntUse(intUse);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("tradTp"))){
            String tradTp = (String) groupInfo.get("tradTp");//取水行业
            wrPdoGroup.setTradTp(tradTp);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("gwoaTp"))){
            String gwoaTp = (String) groupInfo.get("gwoaTp");//地下水超采区类型
            wrPdoGroup.setGroTp(gwoaTp);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("isPipe"))){
            String isPipe = (String) groupInfo.get("isPipe");//管网覆盖
            wrPdoGroup.setIsPipe(isPipe);
        }
        if(ToolUtil.isNotEmpty(groupInfo.get("wrtrTp"))){
            String wrtrTp = (String) groupInfo.get("wrtrTp");//税额等次
            wrPdoGroup.setWrtrTp(wrtrTp);
        }
        return wrPdoGroup;
    }
    /************************************************************/




}
