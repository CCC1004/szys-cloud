package cn.stylefeng.guns.modular.szys.service;

import cn.stylefeng.guns.modular.system.model.WrPdoGroup;
import cn.stylefeng.guns.modular.system.model.WrPdoInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水源分组表 服务类
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
public interface IWrPdoGroupService extends IService<WrPdoGroup> {

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
    List<Map<String, Object>> getWrPdoGroupsPage(Page<WrPdoGroup> page, String groupNm, String wpcSn, String sourTp, String intUse, String tradTp, String gwoaTp, String isPipe, String wrtrTp, String orderByField, boolean asc);


    /*******************水源分组  公共方法：*************/

    /**
     *  分组提交，添加水源分组信息、水源分组与取水口关系表
     * @param groupType   分组类型（1：行政区划；2：取用水户；3：取水许可证）
     */
    void groupSubmit(String groupType);

    void groupSubmit2(String groupType, String opt);



}
