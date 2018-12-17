package cn.stylefeng.guns.modular.szys.service.impl;

import cn.stylefeng.guns.modular.system.dao.WrAdBMapper;
import cn.stylefeng.guns.modular.system.model.WrWiuB;
import cn.stylefeng.guns.modular.system.dao.WrWiuBMapper;
import cn.stylefeng.guns.modular.szys.service.IWrWiuBService;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取用水户信息 服务实现类
 * </p>
 *
 * @author superC
 * @since 2018-11-21
 */
@Service
public class WrWiuBServiceImpl extends ServiceImpl<WrWiuBMapper, WrWiuB> implements IWrWiuBService {

    /**
     * 根据纳税人识别号（或统一社会信用代码）,获取取用水户信息
     */
    @Override
    public WrWiuB selectByAccount(String account) {
        WrWiuB wrWiuB = this.baseMapper.selectByAccount(account);
        return wrWiuB;
    }

    /**
     * 根据条件查询用户列表
     * @param dataScope 数据范围
     * @param wiuNm  取用水户全称
     * @param isHiLev 是否为高耗水行业
     * @param certSta 办证状态
     * @param adlCd 归属行政层级代码
     * @return
     */
    @Override
    public List<Map<String, Object>> selectWrWiuBMapByParams(DataScope dataScope, String wiuNm, String isHiLev, String certSta, String adlCd) {
        return this.baseMapper.selectWrWiuBMapByParams(dataScope, wiuNm, isHiLev, certSta, adlCd);
    }
}
