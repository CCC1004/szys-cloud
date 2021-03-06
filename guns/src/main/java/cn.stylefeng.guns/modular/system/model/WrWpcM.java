package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 取水许可证信息
 * </p>
 *
 * @author ll
 * @since 2018-11-28
 */
@TableName("wr_wpc_m")
public class WrWpcM extends Model<WrWpcM> {

    private static final long serialVersionUID = 1L;

    /**
     * 取水许可证代码
     */
    @TableId("WPC_CD")
    private String wpcCd;
    /**
     * 取水许可证编号
     */
    @TableField("WPC_SN")
    private String wpcSn;
    /**
     * 取水权人名称
     */
    @TableField("WRP_NM")
    private String wrpNm;
    /**
     * 行政区划
     */
    @TableField("AD_CD")
    private String adCd;
    /**
     * 取用水户代码
     */
    @TableField("WIU_CD")
    private String wiuCd;
    /**
     * 取水起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("INT_BD")
    private Date intBd;
    /**
     * 取水终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("INT_DL")
    private Date intDl;
    /**
     * 监督管理单位代码
     */
    @TableField("MAN_ORG_CD")
    private String manOrgCd;
    /**
     * 证件状态
     */
    @TableField("CERT_COND")
    private String certCond;
    /**
     * 证书印刷编号
     */
    @TableField("CERT_PRT_CD")
    private String certPrtCd;
    /**
     * 领取日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("DRAW_DT")
    private Date drawDt;
    /**
     * 领取人员
     */
    @TableField("DRAW_STAFF")
    private String drawStaff;
    /**
     * 时间戳
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("TS")
    private Date ts;
    /**
     * 备注
     */
    @TableField("NT")
    private String nt;


    public String getWpcCd() {
        return wpcCd;
    }

    public void setWpcCd(String wpcCd) {
        this.wpcCd = wpcCd;
    }

    public String getWpcSn() {
        return wpcSn;
    }

    public void setWpcSn(String wpcSn) {
        this.wpcSn = wpcSn;
    }

    public String getWrpNm() {
        return wrpNm;
    }

    public void setWrpNm(String wrpNm) {
        this.wrpNm = wrpNm;
    }

    public String getAdCd() {
        return adCd;
    }

    public void setAdCd(String adCd) {
        this.adCd = adCd;
    }

    public String getWiuCd() {
        return wiuCd;
    }

    public void setWiuCd(String wiuCd) {
        this.wiuCd = wiuCd;
    }

    public Date getIntBd() {
        return intBd;
    }

    public void setIntBd(Date intBd) {
        this.intBd = intBd;
    }

    public Date getIntDl() {
        return intDl;
    }

    public void setIntDl(Date intDl) {
        this.intDl = intDl;
    }

    public String getManOrgCd() {
        return manOrgCd;
    }

    public void setManOrgCd(String manOrgCd) {
        this.manOrgCd = manOrgCd;
    }

    public String getCertCond() {
        return certCond;
    }

    public void setCertCond(String certCond) {
        this.certCond = certCond;
    }

    public String getCertPrtCd() {
        return certPrtCd;
    }

    public void setCertPrtCd(String certPrtCd) {
        this.certPrtCd = certPrtCd;
    }

    public Date getDrawDt() {
        return drawDt;
    }

    public void setDrawDt(Date drawDt) {
        this.drawDt = drawDt;
    }

    public String getDrawStaff() {
        return drawStaff;
    }

    public void setDrawStaff(String drawStaff) {
        this.drawStaff = drawStaff;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    @Override
    protected Serializable pkVal() {
        return this.wpcCd;
    }

    @Override
    public String toString() {
        return "WrWpcM{" +
        ", wpcCd=" + wpcCd +
        ", wpcSn=" + wpcSn +
        ", wrpNm=" + wrpNm +
        ", adCd=" + adCd +
        ", wiuCd=" + wiuCd +
        ", intBd=" + intBd +
        ", intDl=" + intDl +
        ", manOrgCd=" + manOrgCd +
        ", certCond=" + certCond +
        ", certPrtCd=" + certPrtCd +
        ", drawDt=" + drawDt +
        ", drawStaff=" + drawStaff +
        ", ts=" + ts +
        ", nt=" + nt +
        "}";
    }
}
