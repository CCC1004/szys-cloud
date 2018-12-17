package cn.stylefeng.guns.modular.szys.service;

import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划表 服务类
 * </p>
 *
 * @author superc
 * @since 2018-11-21
 */
public interface IWrAdBService extends IService<WrAdB> {

    /**
     * 根据条件查询用户列表
     * @param page 分页对象
     * @param adCd  行政区划代码
     * @param adNm 行政区划名称
     * @param isEnable 行政区划是否启用
     * @param orderByField 排序字段
     * @param asc 排序方式
     * @return
     */
    List<Map<String, Object>> selectWrAdBMapByParams(Page<WrAdB> page, String adCd, String adNm, String isEnable, String orderByField, boolean asc);

}
