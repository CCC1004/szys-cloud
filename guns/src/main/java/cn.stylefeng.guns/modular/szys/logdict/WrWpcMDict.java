/*********************************************************
 ** Description: TODO 
 ** <br><br>
 ** Date: Created in 2018/12/6  16:11
 ** @author 楼兰
 ** @version 0.0.0
 *********************************************************/
package cn.stylefeng.guns.modular.szys.logdict;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

public class WrWpcMDict extends AbstractDictMap
{
	@Override
	public void init()
	{
		put("wpcCd", "取水许可证代码");
		put("wrWpcMId", "取水许可证代码");
		put("wpcSn", "取水许可证编号");
		put("wrpNm", "取水权人名称");
		put("adCd", "行政区划");
		put("wiuCd", "取用水户代码");
		put("intDl", "取水终止日期");
		put("intBd", "取水起始日期");
		put("manOrgCd", "监督管理单位代码");
		put("certCond", "证件状态");
		put("certPrtCd", "证书印刷编号");
		put("drawDt", "领取日期");
		put("drawStaff", "领取人员");

	}

	@Override
	protected void initBeWrapped()
	{

	}
}
