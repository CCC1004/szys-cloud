package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划表 Mapper 接口
 * </p>
 *
 * @author superc
 * @since 2018-11-21
 */
public interface WrAdBMapper extends BaseMapper<WrAdB> {

    /**
     * 根据条件查询用户列表
     * @param page 分页对象
     * @param adCd  行政区划代码
     * @param adNm 行政区划名称
     * @param isEnable 行政区划是否启用
     * @param orderByField 排序字段
     * @param isAsc 排序方式
     * @return
     */
    List<Map<String, Object>> selectWrAdBMapByParams(@Param("page") Page<WrAdB> page,
                                                     @Param("adCd") String adCd,
                                                     @Param("adNm") String adNm,
                                                     @Param("isEnable") String isEnable,
                                                     @Param("orderByField") String orderByField,
                                                     @Param("isAsc") boolean isAsc);

}
