/*********************************************************
 ** Description: TODO 
 ** <br><br>
 ** Date: Created in 2018/12/6  15:37
 ** @author 楼兰
 ** @version 0.0.0
 *********************************************************/
package cn.stylefeng.guns.modular.szys.logdict;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

public class WrPdoInfoDict extends AbstractDictMap
{
	@Override
	public void init()
	{
		put("intNm","取水口名称");
		put("wrPdoInfoId","取水口ID号");
		put("uuid", "取水口代码");
		put("sn", "取水口序号");
		put("wpcCd", "取水口许可证编码");
		put("wpcSn", "取水口许可证证号");
		put("intBd", "取水口许可证开始日期");
		put("intDl", "取水口许可证结束日期");
		put("intDl", "取水口许可证结束日期");
		put("wiuCd", "取用水户编码");
		put("sourTp", "水源类型");
		put("sate", "水源状态");
		put("lttd", "维度");
		put("lgtd", "精度");
		put("groTp", "地下水类型");
		put("intTp", "取水方式");
		put("addr", "所在地址");
		put("wrzCd", "取水口所在水资源分区");
		put("adCd", "取水口所在行政分区");
		put("wrtrTp", "税额等次");
		put("isPipe", "管网覆盖");
		put("gwoaTp", "地下水超采区类型");
		put("tradTp", "取水行业");
		put("intUse", "取水用途");



	}

	@Override
	protected void initBeWrapped()
	{

	}
}
