package cn.stylefeng.guns.modular.szys.utils;

import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.factory.MutiStrFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.dao.DictMapper;
import cn.stylefeng.guns.modular.system.model.Dept;
import cn.stylefeng.guns.modular.system.model.Dict;
import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.guns.modular.system.service.IDeptService;
import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.properties.ResourcesProperties;
import cn.stylefeng.guns.modular.szys.service.IWrWiuBService;
import cn.stylefeng.roses.core.util.SpringContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @Author: superC
 * @Date: 2018/11/22 13:21
 */
public class CommonUtils {

    /**
     * 获取uuid（32位）
     * @return
     */
    public static String getGuid(){
        String guid = UUID.randomUUID().toString().replaceAll("-", "");
        return guid;
    }

    /**
     * 获取系统当前时间
     */
    public static Date getCreateTime(){
        Date date = new Date();
        return date;
    }

    /**
     * 判断当前用户是否是水行政主管部门
     */
    public static boolean isWaterManager(){
        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();
        // 角色集
        List<Integer> roleList = user.getRoleList();

        ResourcesProperties resourcesProperties = SpringContextHolder.getBean(ResourcesProperties.class);
        //判断当前用户角色集中，是否包含水行政主管部门
        boolean contains = roleList.contains(resourcesProperties.getWaterManager());
        return contains;
    }

    /**
     * 判断当前用户是否是取用水户
     */
    public static boolean isWaterget(){
        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();
        // 角色集
        List<Integer> roleList = user.getRoleList();

        ResourcesProperties resourcesProperties = SpringContextHolder.getBean(ResourcesProperties.class);
        //判断当前用户角色集中，是否包含水行政主管部门
        boolean contains = roleList.contains(resourcesProperties.getWaterUser());
        return contains;
    }

    /**
     *  根据当前用户所在部门，获取行政区划id
     */
    public static String getAdid(){

        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();
        //当前用户所在部门
        Integer deptId = user.getDeptId();

        IDeptService deptService = SpringContextHolder.getBean(IDeptService.class);
        Dept dept = deptService.selectById(deptId);
        //行政区划id
        String adid = dept.getAdid();
        return adid;
    }


    /**
     * 根据行政区划id，获取用于模糊查询该行政区划及其子集的编码片段
     * @param adid  行政区划id
     * @return  id编码片段
     */
    public static String getAdidLike(String adid){
        //用于查询行政区划及其子集
        String adicLike = "";
        // ******************如果行政区划是6位，
        //市
        String city = adid.substring(2, 4);
        //县
        String xian = adid.substring(4, 6);
        if(city.equals("00") && xian.equals("00")){
            adicLike = adid.substring(0, 2);
        }else if(!city.equals("00") && xian.equals("00")){
            adicLike = adid.substring(0,4);
        }else {
            adicLike = adid;
        }
        return adicLike;
    }

    /**
     * 根据行政区划id，获取用于模糊查询该行政区划及其子集的编码片段
     * @param adid  行政区划id
     * @return  id编码片段
     */
    public static String getAdidNextLike(String adid){
        //用于查询行政区划及其子集
        String adicLike = "";
        // ******************如果行政区划是6位，
        //市
        String city = adid.substring(2, 4);
        //县
        String xian = adid.substring(4, 6);
        if(city.equals("00") && xian.equals("00")){
            adicLike = adid.substring(0, 2);
        }else if(!city.equals("00") && xian.equals("00")){
            adicLike = adid.substring(0,4);
        }else {
            adicLike = adid;
        }
        return adicLike;
    }

    /**
     * 获取当前登陆人id
     */
    public static Integer getCurrentUserId ()
    {
        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();

        return user.getId();
    }

    /**
     * 获取当前登陆人ACCOUNT
     */
    public static String getCurrentUserAccount ()
    {
        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();

        return user.getAccount();
    }

    /**
     * 根据当前用户登录名，获取取用水户的信息
     */
    public static WrWiuB getWrWiuBInfoByUsername(){

        //获取当前用户登录信息
        ShiroUser user = ShiroKit.getUser();
        //登录名
        String account = user.getAccount();

        IWrWiuBService wrWiuBService = SpringContextHolder.getBean(IWrWiuBService.class);
        //
        WrWiuB wrWiuB = wrWiuBService.selectByAccount(account);
        return wrWiuB;

    }


    private static DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    /*
     * 初始化先缓冲中添加数据字典
     * */
    public static void addCacheForDict ()
    {
        List<Dict> listObjId = dictMapper.listObjId() ; //顶级父
        List<Dict> listObjPid = dictMapper.listObjPid(); //二级子

        for (Dict dictP : listObjId)
        {
            Map<String, String> map = new HashMap<>();
            for (Dict dictS : listObjPid)
            {
                if (dictS.getPid().equals(dictP.getId()))
                {
                    map.put(dictS.getCode().toString(), dictS.getName());
                }
            }
            CacheUtil.put(Cache.DICT, dictP.getCode(),map);
        }
    }


    /*
    * 向缓冲中添加一个字典数据
    * */
    public static void addCacheForHtml (String dictCode,String dictValue)
    {
        List<Map<String, String>> mapList = MutiStrFactory.parseKeyValue(dictValue);
        Map<String, String> map = new HashMap<>();

        for (Map<String, String> mapDict : mapList)
        {
            map.put(mapDict.get("CODE"), mapDict.get("NAME"));
        }
        CacheUtil.put(Cache.DICT,dictCode,map);
    }

    /*
    * 向缓冲中修改一个字典数据
    * */
    public static void fixCacheForHtml (String dictCode,String dictValue)
    {
        Map<String,String> mapOld = (Map<String,String>)CacheUtil.get(Cache.DICT, dictCode);

        List<Map<String, String>> mapList = MutiStrFactory.parseKeyValue(dictValue);


        for (Map<String, String> mapDict : mapList)
        {
            mapOld.put(mapDict.get("CODE"), mapDict.get("NAME"));
        }
        CacheUtil.put(Cache.DICT,dictCode,mapOld);
    }

    /*
    * 向缓冲中删除一个字典数据
    * */
    public static void deleteCacheForHtml (String dictId)
    {
        Dict dict = dictMapper.selectById(dictId);
        CacheUtil.remove(Cache.DICT,dict.getCode());
    }

    /**
     * Description : 从字典中获取数据
     * @para
     * @return	对应id的，在字典中的数据
     * @exception
     * */
    public static String getDictForCache (String mark,String id)
    {
        Map<String, String> mapOld = (Map<String, String>) CacheUtil.get(Cache.DICT, DictEnum.valueOfName(mark));
        String nm = mapOld.get(id);
        return "null".equals(nm)?"":nm ;
    }

    /**
     * 实体对象转成Map
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
