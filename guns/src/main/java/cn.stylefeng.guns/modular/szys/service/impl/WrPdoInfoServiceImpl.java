package cn.stylefeng.guns.modular.szys.service.impl;

import cn.stylefeng.guns.modular.system.dao.WrPdoGroupMapper;
import cn.stylefeng.guns.modular.system.dao.WrPdoInfoMapper;
import cn.stylefeng.guns.modular.system.dao.WrPdoPdogroupRelMapper;
import cn.stylefeng.guns.modular.system.model.WrPdoGroup;
import cn.stylefeng.guns.modular.system.model.WrPdoInfo;
import cn.stylefeng.guns.modular.system.model.WrPdoPdogroupRel;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.guns.modular.szys.service.IWrPdoInfoService;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取水口信息表o 服务实现类
 * </p>
 *
 * @author yang
 * @since 2018-11-26
 */
@Service
public class WrPdoInfoServiceImpl extends ServiceImpl<WrPdoInfoMapper, WrPdoInfo> implements IWrPdoInfoService {

    @Autowired
    private ResourcesProperties resourcesProperties;

    @Autowired
    private WrPdoPdogroupRelMapper wrPdoPdogroupRelMapper;

    @Autowired
    private WrPdoGroupMapper wrPdoGroupMapper;

    @Override
    public List<Map<String, Object>> selectByWiucd(String wiuCd)
    {
        return super.baseMapper.selectByWiucd(wiuCd);
    }

    @Override
    public List<Map<String, Object>> selectByWiucd2(String wiuCd)
    {
        return super.baseMapper.selectByWiucd2(wiuCd);
    }

    private Logger logger = LoggerFactory.getLogger(WrPdoInfoServiceImpl.class);

    /**
     * 新增取水口，并更正水源分组
     * @param wrPdoInfo
     */
    @Override
    public void insertPdoAndCorrectGroup(WrPdoInfo wrPdoInfo) {
         /*
            新增取水口
         */
        boolean b = SqlHelper.delBool(this.baseMapper.insert(wrPdoInfo));

        if(b){
             /*
                更正水源分组
             */

             //根据该取水口的分组要素，先判断其相关的取水口是否已分组



        }

    }

    /**
     * 删除取水口，并更正水源分组
     * @param wrPdoInfoId 取水口id
     */
    @Override
    @Transactional
    public void deletePdoAndCorrectGroup(String wrPdoInfoId) {
        /*
            删除取水口
         */
        boolean b = SqlHelper.delBool(this.baseMapper.deleteById(wrPdoInfoId));

        if(b){
            /*
                更正水源分组
             */

            String status = resourcesProperties.getGroupRelUse();
            //根据取水口id对应的水源分组id，获取该水源分组下的所有取水口关联信息
            List<WrPdoPdogroupRel> relList =  wrPdoPdogroupRelMapper.getRelListByGroupid(wrPdoInfoId, status);
           if(relList==null && relList.size()==0){  //该取水口未进行分组，不操作

           }else if(relList.size()==1){  //水源分组下，只有这一个取水口，则将该关联信息状态改为2，并删除该水源分组信息

               //更改关联信息状态
               WrPdoPdogroupRel rel = relList.get(0);
               rel.setStatus(resourcesProperties.getGroupRelDelete());//状态（2:弃用）
               rel.setTs(CommonUtils.getCreateTime());
               wrPdoPdogroupRelMapper.update(rel,null);

               //删除水源分组信息
               wrPdoGroupMapper.deleteById(rel.getPdoGroupId());

           }else if(relList.size()>1){  //水源分组下，有多个取水口，则将该关联信息状态改为2

               for (WrPdoPdogroupRel rel : relList){
                   if(rel.getPdoId().equals(wrPdoInfoId)){
                       //更改关联信息状态
                       rel.setStatus(resourcesProperties.getGroupRelDelete());//状态（2:弃用）
                       rel.setTs(CommonUtils.getCreateTime());
                        wrPdoPdogroupRelMapper.updateById(rel);
                    }
               }

           }
        }
    }

    /**
     * 修改取水口，并更正水源分组
     * @param wrPdoInfo 取水口信息
     */
    @Override
    @Transactional
    public void updatePdoAndCorrectGroup(WrPdoInfo wrPdoInfo) {

        /*
            修改取水口
         */
        boolean b = SqlHelper.delBool(this.baseMapper.updateById(wrPdoInfo));

        if(b){
             /*
                更正水源分组
             */

             //判断该取水口是否已经分组
            Wrapper<WrPdoPdogroupRel> wrapper = new EntityWrapper<WrPdoPdogroupRel>();
            wrapper.eq("PDO_ID",wrPdoInfo.getUuid());
            wrapper.eq("STATUS",resourcesProperties.getGroupRelUse());
            List<WrPdoPdogroupRel> relList = wrPdoPdogroupRelMapper.selectList(wrapper);
            if(relList.size()>0){//已分组
                /*
                    前提：一个取水口，只能使用一种分组方式。即一个取水口，只能查到一个水源分组对象
                 */
                WrPdoPdogroupRel wrPdoPdogroupRel = relList.get(0);
                WrPdoGroup wrPdoGroup = wrPdoGroupMapper.selectById(wrPdoPdogroupRel.getPdoGroupId());
                //判断分组要素是否更改
                boolean eq = isPdoEqGroup(wrPdoInfo, wrPdoGroup);
                if(eq){
                    logger.info("分组要素没有修改，不需要更改分组");
                }else{

                }

            }else{
                logger.info("该取水口还未分组");
            }
        }
    }

    /**
     * 判断分组要素是否更改
     * @param wrPdoInfo 修改后的取水口信息   
     * @param wrPdoGroup    水源分组信息
     * @return   true 相等 ；false 不等
     */
    private boolean isPdoEqGroup(WrPdoInfo wrPdoInfo, WrPdoGroup wrPdoGroup) {

        //六要素
        boolean sourTp = eqCloumn(wrPdoInfo.getSourTp(), wrPdoGroup.getSourTp());
        if(!sourTp){ return false; }
        boolean intUse = eqCloumn(wrPdoInfo.getIntUse(), wrPdoGroup.getIntUse());
        if(!intUse){ return false; }
        boolean tradTp = eqCloumn(wrPdoInfo.getTradTp(), wrPdoGroup.getTradTp());
        if(!tradTp){ return false; }
        boolean gwoaTp = eqCloumn(wrPdoInfo.getGwoaTp(), wrPdoGroup.getGwoaTp());
        if(!gwoaTp){ return false; }
        boolean isPipe = eqCloumn(wrPdoInfo.getIsPipe(), wrPdoGroup.getIsPipe());
        if(!isPipe){ return false; }
        boolean wrtrTp = eqCloumn(wrPdoInfo.getWrtrTp(), wrPdoGroup.getWrtrTp());
        if(!wrtrTp){ return false; }
        //许可证
        boolean wpcCd = eqCloumn(wrPdoInfo.getWpcCd(), wrPdoGroup.getWpcCd());
        if(!wpcCd){  return false;  }
        //取用水户
        boolean wiuCd = eqCloumn(wrPdoInfo.getWiuCd(), wrPdoGroup.getWiuCd());
        if(!wiuCd){  return false;  }
        //行政区划
        boolean acCd = eqCloumn(wrPdoInfo.getAdCd(), wrPdoGroup.getAdCd());
        if(!acCd){  return false;  }

        return true;
    }

    /**
     * 对比2个对象中的某个字段是否相等
     * @param c1
     * @param c2
     * @return  true 相等 ；false 不等
     */
    private boolean eqCloumn(String c1, String c2) {
        if(ToolUtil.isNotEmpty(c1) && ToolUtil.isNotEmpty(c2)){
            if(c1.equals(c2)){
                return true;
            }else {
                return false;
            }
        }else if(ToolUtil.isEmpty(c1) && ToolUtil.isEmpty(c2)) {
            return true;
        }else {
            return false;
        }
    }


}
