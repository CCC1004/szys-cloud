package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WrPdoGroup;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水源分组表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
public interface WrPdoGroupMapper extends BaseMapper<WrPdoGroup> {

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
    List<Map<String, Object>> getWrPdoGroupsPage(@Param("page") Page<WrPdoGroup> page,
                                                 @Param("groupNm") String groupNm,
                                                 @Param("wpcSn") String wpcSn,
                                                 @Param("sourTp") String sourTp,
                                                 @Param("intUse") String intUse,
                                                 @Param("tradTp") String tradTp,
                                                 @Param("gwoaTp") String gwoaTp,
                                                 @Param("isPipe") String isPipe,
                                                 @Param("wrtrTp") String wrtrTp,
                                                 @Param("orderByField") String orderByField,
                                                 @Param("asc") boolean asc);
}
