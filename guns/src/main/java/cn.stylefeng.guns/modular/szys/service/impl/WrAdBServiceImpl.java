package cn.stylefeng.guns.modular.szys.service.impl;

import cn.stylefeng.guns.modular.system.model.WrAdB;
import cn.stylefeng.guns.modular.system.dao.WrAdBMapper;
import cn.stylefeng.guns.modular.szys.service.IWrAdBService;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划表 服务实现类
 * </p>
 *
 * @author superc
 * @since 2018-11-21
 */
@Service
public class WrAdBServiceImpl extends ServiceImpl<WrAdBMapper, WrAdB> implements IWrAdBService {

    @Override
    public List<Map<String, Object>> selectWrAdBMapByParams(Page<WrAdB> page, String adCd, String adNm, String isEnable, String orderByField, boolean asc) {
        return this.baseMapper.selectWrAdBMapByParams(page,adCd,adNm,isEnable,orderByField,asc);
    }
}
