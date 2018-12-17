/*********************************************************
 ** Description: TODO 
 ** <br><br>
 ** Date: Created in 2018/12/3  13:33
 ** @author 楼兰
 ** @version 0.0.0
 *********************************************************/
package cn.stylefeng.guns.modular.szys.warpper;

import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.szys.domain.DictEnum;
import cn.stylefeng.guns.modular.szys.utils.CommonUtils;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/*
* wrpdoinfo包装类
* */
public class WrPdoInfoWarpper extends BaseControllerWrapper
{

	public WrPdoInfoWarpper(Map<String, Object> single)
	{
		super(single);
	}

	public WrPdoInfoWarpper(List<Map<String, Object>> multi)
	{
		super(multi);
	}

	public WrPdoInfoWarpper(Page<Map<String, Object>> page)
	{
		super(page);
	}

	public WrPdoInfoWarpper(PageResult<Map<String, Object>> pageResult)
	{
		super(pageResult);
	}



	@Override
	protected void wrapTheMap(Map<String, Object> map)
	{
		map.put("sourTpNm", CommonUtils.getDictForCache("sourTp", map.get("sourTp").toString()));
		map.put("intUseNm", CommonUtils.getDictForCache("intUse", map.get("intUse").toString()));
		map.put("tradTpNm", CommonUtils.getDictForCache("tradTp", map.get("tradTp").toString()));
		map.put("gwoaTpNm", CommonUtils.getDictForCache("gwoaTp", map.get("gwoaTp").toString()));
		map.put("isPipeNm", CommonUtils.getDictForCache("isPipe", map.get("isPipe").toString()));
		map.put("wrtrTpNm", CommonUtils.getDictForCache("wrtrTp", map.get("wrtrTp").toString()));
		map.put("stateNm", CommonUtils.getDictForCache("state", map.get("state").toString()));
	}
}
