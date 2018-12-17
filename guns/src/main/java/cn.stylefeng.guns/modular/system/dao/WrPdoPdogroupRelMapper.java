package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WrPdoPdogroupRel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 水源分组与取水口信息中间关系表 Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
@Component(value = "wrPdoPdogroupRelMapper")
public interface WrPdoPdogroupRelMapper extends BaseMapper<WrPdoPdogroupRel> {

    /**
     *  根据取水口id获取其相关水源分组的所有取水口信息
     * @param wrPdoInfoId 取水口id
     * @param status 状态，
     * @return
     */
    List<WrPdoPdogroupRel> getRelListByGroupid(@Param("wrPdoInfoId") String wrPdoInfoId,
                                               @Param("status") String status);
}
