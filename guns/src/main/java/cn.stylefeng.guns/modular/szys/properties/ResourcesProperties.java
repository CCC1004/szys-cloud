package cn.stylefeng.guns.modular.szys.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 资源信息配置
 * @Author: superC
 * @Date: 2018/11/11 11:24
 *  PropertySource注解解释：
 *      value：加载classpath路径中config文件,可以一次性加载多个。value= {"a.yml,b.yml"}
 *      ignoreResourceNotFound:  当指定的配置文件不存在是否报错，默认是false;
 *      如果该文件不存在，则ignoreResourceNotFound为true的时候，程序不会报错，如果ignoreResourceNotFound为false的时候，程序直接报错。
 *      实际项目开发中，最好设置ignoreResourceNotFound为false。
 *      encoding:  用于指定读取属性文件所使用的编码，我们通常使用的是UTF-8
 */
@Component
@ConfigurationProperties(prefix = "param")
@PropertySource(value = "classpath:resources.yml",ignoreResourceNotFound = false,encoding = "utf-8")
public class ResourcesProperties {

    /**
     *   添加取用水户的时候，后台添加用户信息设置项
     */
    @Value("${dePassword}")
    private String dePassword;

    @Value("${deType}")
    private String deType;

    @Value("${deRole}")
    private String deRole;


    /**
     * 角色对应的id
     */
    @Value("${super_admin}")
    private Integer superAdmin;

    @Value("${water_manager}")
    private Integer waterManager;

    @Value("${water_user}")
    private Integer waterUser;


    /**
     * 对取水口进行分组时，所选类型对应的值
     */
    @Value("${addv_type}")
    private String addvType;

    @Value("${wiub_type}")
    private String wiubType;

    @Value("${wpc_type}")
    private String wpcType;


    /**
     * 水源分组与取水口信息中间关系表 数据的状态
     */
    @Value("${group_rel_use}")
    private String groupRelUse;

    @Value("${group_rel_delete}")
    private String groupRelDelete;




    /**
     * setter/getter
     */
    public Integer getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Integer superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Integer getWaterManager() {
        return waterManager;
    }

    public void setWaterManager(Integer waterManager) {
        this.waterManager = waterManager;
    }

    public Integer getWaterUser() {
        return waterUser;
    }

    public void setWaterUser(Integer waterUser) {
        this.waterUser = waterUser;
    }

    public String getDeRole() {
        return deRole;
    }

    public void setDeRole(String deRole) {
        this.deRole = deRole;
    }

    public String getDePassword() {
        return dePassword;
    }

    public void setDePassword(String dePassword) {
        this.dePassword = dePassword;
    }

    public String getDeType() {
        return deType;
    }

    public void setDeType(String deType) {
        this.deType = deType;
    }

    public String getAddvType() {
        return addvType;
    }

    public void setAddvType(String addvType) {
        this.addvType = addvType;
    }

    public String getWiubType() {
        return wiubType;
    }

    public void setWiubType(String wiubType) {
        this.wiubType = wiubType;
    }

    public String getWpcType() {
        return wpcType;
    }

    public void setWpcType(String wpcType) {
        this.wpcType = wpcType;
    }

    public String getGroupRelUse() {
        return groupRelUse;
    }

    public void setGroupRelUse(String groupRelUse) {
        this.groupRelUse = groupRelUse;
    }

    public String getGroupRelDelete() {
        return groupRelDelete;
    }

    public void setGroupRelDelete(String groupRelDelete) {
        this.groupRelDelete = groupRelDelete;
    }

}
