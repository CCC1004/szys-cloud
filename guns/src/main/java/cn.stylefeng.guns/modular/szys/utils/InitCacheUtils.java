package cn.stylefeng.guns.modular.szys.utils;

import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.cache.CacheKey;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.dao.WrAdBMapper;
import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;

import java.util.List;

/**
 * 初始化时，向缓存添加数据
 * @Author: superC
 * @Date: 2018/12/3 14:28
 */
public class InitCacheUtils {

    private static WrAdBMapper wrAdBMapper = SpringContextHolder.getBean(WrAdBMapper.class);

    /**
     * 初始化，将所有行政区划信息存储至缓存
     */
    public static void addCacheForAddv() {

        List<WrAdB> list = wrAdBMapper.selectList(null);
        for (WrAdB wrAdB : list){
            //行政区划编码、名称不为空
            if(ToolUtil.isNotEmpty(wrAdB) && ToolUtil.isNotEmpty(wrAdB.getAdCd()) && ToolUtil.isNotEmpty(wrAdB.getAdNm())){
                CacheUtil.put(Cache.ADDV, CacheKey.ADDV+wrAdB.getAdCd(), wrAdB);
            }
        }

    }

}
