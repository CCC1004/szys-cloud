package cn.stylefeng.guns.modular.szys.service;

import cn.stylefeng.guns.modular.system.model.WrPdoInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 取水口信息表o 服务类
 * </p>
 *
 * @author yang
 * @since 2018-11-26
 */
public interface IWrPdoInfoService extends IService<WrPdoInfo> {


    List<Map<String, Object>> selectByWiucd(String wiuCd);

    List<Map<String, Object>> selectByWiucd2(String wiuCd);


    /**
     * 新增取水口，并更正水源分组
     * @param wrPdoInfo
     */
    void insertPdoAndCorrectGroup(WrPdoInfo wrPdoInfo);

    /**
     * 删除取水口，并更正水源分组
     * @param wrPdoInfoId 取水口id
     */
    void deletePdoAndCorrectGroup(String wrPdoInfoId);

    /**
     * 修改取水口，并更正水源分组
     * @param wrPdoInfo 取水口信息
     */
    void updatePdoAndCorrectGroup(WrPdoInfo wrPdoInfo);


}
