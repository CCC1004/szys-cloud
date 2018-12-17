package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WrPdoInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取水口信息表o Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2018-11-26
 */
@Component(value = "wrPdoInfoMapper")
public interface WrPdoInfoMapper extends BaseMapper<WrPdoInfo> {


    List<Map<String, Object>> selectByWiucd(@Param("wiuCd") String wiuCd);

    List<Map<String, Object>> selectByWiucd2(@Param("wiuCd") String wiuCd);


    /**
     * 根据行政区划id，获取其取用水户，从而获取取水口列表,并进行分组
     * @param adidLike
     * @return
     */
    List<Map<String, Object>> getGroupMapByUuid(@Param("adidLike") String adidLike);


    List<Map<String, Object>> getGroupMapByWiuCd(@Param("optWiuCd") String optWiuCd);


    /**
     * 根据行政区划，获取取水口列表,并进行分组
     * @param adidLike
     * @return
     */
    List<Map<String, Object>> getGroupMapByAdidLike(@Param("adidLike") String adidLike);


    List<Map<String, Object>> getGroupMapByWpcM(@Param("adidLike") String adidLike);

    List<Map<String, Object>> getGroupMapByWpcCd(@Param("optWpcCd") String optWpcCd);


    /**
     * 根据分组信息，获取其取水口信息列表
     * @param adidLike
     * @param isWpcNull
     * @param sourTp
     * @param intUse
     * @param tradTp
     * @param gwoaTp
     * @param isPipe
     * @param wrtrTp
     * @return
     */
    List<WrPdoInfo> getPdoListByGroupInfo(@Param("adidLike") String adidLike,
                                          @Param("isWpcNull") String isWpcNull,
                                          @Param("sourTp") String sourTp,
                                          @Param("intUse") String intUse,
                                          @Param("tradTp") String tradTp,
                                          @Param("gwoaTp") String gwoaTp,
                                          @Param("isPipe") String isPipe,
                                          @Param("wrtrTp") String wrtrTp,
                                          @Param("wpcCd") String wpcCd,
                                          @Param("wiuCd") String wiuCd,
                                          @Param("acCd") String acCd
    );

    List<WrPdoInfo> getPdoListByGroupInfoAndWiuCd(@Param("optWiuCd") String optWiuCd,
                                                  @Param("isWpcNull") String isWpcNull,
                                                  @Param("sourTp") String sourTp,
                                                  @Param("intUse") String intUse,
                                                  @Param("tradTp") String tradTp,
                                                  @Param("gwoaTp") String gwoaTp,
                                                  @Param("isPipe") String isPipe,
                                                  @Param("wrtrTp") String wrtrTp,
                                                  @Param("wpcCd") String wpcCd,
                                                  @Param("wiuCd") String wiuCd,
                                                  @Param("acCd") String acCd
    );

    List<WrPdoInfo> getPdoListByGroupInfoAndAdidLike(@Param("adidLike") String adidLike,
                                                     @Param("isWpcNull") String isWpcNull,
                                                     @Param("sourTp") String sourTp,
                                                     @Param("intUse") String intUse,
                                                     @Param("tradTp") String tradTp,
                                                     @Param("gwoaTp") String gwoaTp,
                                                     @Param("isPipe") String isPipe,
                                                     @Param("wrtrTp") String wrtrTp,
                                                     @Param("wpcCd") String wpcCd,
                                                     @Param("wiuCd") String wiuCd,
                                                     @Param("acCd") String acCd
    );

    List<WrPdoInfo> getPdoListByGroupInfoAndAdid(@Param("adid") String adid,
                                                 @Param("isWpcNull") String isWpcNull,
                                                 @Param("sourTp") String sourTp,
                                                 @Param("intUse") String intUse,
                                                 @Param("tradTp") String tradTp,
                                                 @Param("gwoaTp") String gwoaTp,
                                                 @Param("isPipe") String isPipe,
                                                 @Param("wrtrTp") String wrtrTp,
                                                 @Param("wpcCd") String wpcCd,
                                                 @Param("wiuCd") String wiuCd,
                                                 @Param("acCd") String acCd
    );

    List<WrPdoInfo> getPdoListByGroupInfoAndWpcM(@Param("adidLike") String adidLike,
                                                 @Param("isWpcNull") String isWpcNull,
                                                 @Param("sourTp") String sourTp,
                                                 @Param("intUse") String intUse,
                                                 @Param("tradTp") String tradTp,
                                                 @Param("gwoaTp") String gwoaTp,
                                                 @Param("isPipe") String isPipe,
                                                 @Param("wrtrTp") String wrtrTp,
                                                 @Param("wpcCd") String wpcCd,
                                                 @Param("wiuCd") String wiuCd,
                                                 @Param("acCd") String acCd
    );

    List<WrPdoInfo> getPdoListByGroupInfoAndWpcCd(@Param("optWpcCd") String optWpcCd,
                                                  @Param("isWpcNull") String isWpcNull,
                                                  @Param("sourTp") String sourTp,
                                                  @Param("intUse") String intUse,
                                                  @Param("tradTp") String tradTp,
                                                  @Param("gwoaTp") String gwoaTp,
                                                  @Param("isPipe") String isPipe,
                                                  @Param("wrtrTp") String wrtrTp,
                                                  @Param("wpcCd") String wpcCd,
                                                  @Param("wiuCd") String wiuCd,
                                                  @Param("acCd") String acCd
    );


    /**
     * 根据行政区划，获取取水口列表,并进行分组
     * @param adid
     * @return
     */
    List<Map<String, Object>> getGroupMapByAdid(@Param("adid") String adid);

}
