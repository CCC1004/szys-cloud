package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取用水户信息 Mapper 接口
 * </p>
 *
 * @author superC
 * @since 2018-11-21
 */
public interface WrWiuBMapper extends BaseMapper<WrWiuB> {

    /**
     * 根据纳税人识别号（当前用户登录名），获取取用水信息
     * @param account 用户的登录名，即对应取用水户的纳税人识别号
     * @return
     */
    WrWiuB selectByAccount(@Param("account") String account);

    /**
     * 根据条件查询用户列表
     * @param dataScope 数据范围
     * @param wiuNm  取用水户全称
     * @param isHiLev 是否为高耗水行业
     * @param certSta 办证状态
     * @param adlCd 归属行政层级代码
     * @return
     */
    List<Map<String, Object>> selectWrWiuBMapByParams(@Param("dataScope") DataScope dataScope,
                                                      @Param("wiuNm") String wiuNm,
                                                      @Param("isHiLev") String isHiLev,
                                                      @Param("certSta") String certSta,
                                                      @Param("adlCd") String adlCd);
}
