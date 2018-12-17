package cn.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 取用水户信息
 * </p>
 *
 * @author superC
 * @since 2018-11-21
 */
@TableName("wr_wiu_b")
public class WrWiuB extends Model<WrWiuB> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码（UUID）
     */
    @TableId("UUID")
    private String uuid;
    /**
     * 登记序号（金三唯一标识纳税人）
     */
    @TableField("DJXH")
    private String djxh;
    /**
     * 纳税人识别号（或统一社会信用代码）
     */
    @TableField("NSRSBH")
    private String nsrsbh;
    /**
     * 取用水户全称
     */
    @TableField("WIU_NM")
    private String wiuNm;
    /**
     * 责任人姓名
     */
    @TableField("LR_NM")
    private String lrNm;
    /**
     * 取用水户性质
     */
    @TableField("LAW_ATT")
    private String lawAtt;
    /**
     * 取用水户类型
     */
    @TableField("USER_TP")
    private String userTp;
    /**
     * 行业类别
     */
    @TableField("TRAD_TP")
    private String tradTp;
    /**
     * 是否为高耗水行业
     */
    @TableField("IS_HI_LEV")
    private String isHiLev;
    /**
     * 经济类型
     */
    @TableField("ECO_TP")
    private String ecoTp;
    /**
     * 监控级别
     */
    @TableField("MON_G")
    private String monG;
    /**
     * 取用类别
     */
    @TableField("WIU_TP")
    private String wiuTp;
    /**
     * 取水规模(万m3)
     */
    @TableField("YR_WU")
    private BigDecimal yrWu;
    /**
     * 办证状态
     */
    @TableField("CERT_STA")
    private String certSta;
    /**
     * 是否重点监控取用水户
     */
    @TableField("IS_VIMP")
    private String isVimp;
    /**
     * 归属行政层级代码
     */
    @TableField("ADL_CD")
    private String adlCd;
    /**
     * 取水所在属地水利机关
     */
    @TableField("SLJG_CD")
    private String sljgCd;
    /**
     * 取水所在属地税务机关
     */
    @TableField("SWJG_CD")
    private String swjgCd;
    /**
     * 网址
     */
    @TableField("WEB")
    private String web;
    /**
     * 邮箱
     */
    @TableField("E_MAIL")
    private String eMail;
    /**
     * 地址
     */
    @TableField("ADDR")
    private String addr;
    /**
     * 邮政编码
     */
    @TableField("ZIP")
    private String zip;
    /**
     * 办公室电话
     */
    @TableField("TEL")
    private String tel;
    /**
     * 传真号码
     */
    @TableField("FAX")
    private String fax;
    /**
     * 在用状态
     */
    @TableField("ISUSE")
    private String isuse;
    /**
     * 营业状态
     */
    @TableField("RUN_STA")
    private String runSta;
    /**
     * 时间戳
     */
    @TableField("TS")
    private Date ts;
    /**
     * 经度
     */
    @TableField("LGTD")
    private BigDecimal lgtd;
    /**
     * 纬度
     */
    @TableField("LTTD")
    private BigDecimal lttd;
    /**
     * 备注
     */
    @TableField("NT")
    private String nt;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getWiuNm() {
        return wiuNm;
    }

    public void setWiuNm(String wiuNm) {
        this.wiuNm = wiuNm;
    }

    public String getLrNm() {
        return lrNm;
    }

    public void setLrNm(String lrNm) {
        this.lrNm = lrNm;
    }

    public String getLawAtt() {
        return lawAtt;
    }

    public void setLawAtt(String lawAtt) {
        this.lawAtt = lawAtt;
    }

    public String getUserTp() {
        return userTp;
    }

    public void setUserTp(String userTp) {
        this.userTp = userTp;
    }

    public String getTradTp() {
        return tradTp;
    }

    public void setTradTp(String tradTp) {
        this.tradTp = tradTp;
    }

    public String getIsHiLev() {
        return isHiLev;
    }

    public void setIsHiLev(String isHiLev) {
        this.isHiLev = isHiLev;
    }

    public String getEcoTp() {
        return ecoTp;
    }

    public void setEcoTp(String ecoTp) {
        this.ecoTp = ecoTp;
    }

    public String getMonG() {
        return monG;
    }

    public void setMonG(String monG) {
        this.monG = monG;
    }

    public String getWiuTp() {
        return wiuTp;
    }

    public void setWiuTp(String wiuTp) {
        this.wiuTp = wiuTp;
    }

    public BigDecimal getYrWu() {
        return yrWu;
    }

    public void setYrWu(BigDecimal yrWu) {
        this.yrWu = yrWu;
    }

    public String getCertSta() {
        return certSta;
    }

    public void setCertSta(String certSta) {
        this.certSta = certSta;
    }

    public String getIsVimp() {
        return isVimp;
    }

    public void setIsVimp(String isVimp) {
        this.isVimp = isVimp;
    }

    public String getAdlCd() {
        return adlCd;
    }

    public void setAdlCd(String adlCd) {
        this.adlCd = adlCd;
    }

    public String getSljgCd() {
        return sljgCd;
    }

    public void setSljgCd(String sljgCd) {
        this.sljgCd = sljgCd;
    }

    public String getSwjgCd() {
        return swjgCd;
    }

    public void setSwjgCd(String swjgCd) {
        this.swjgCd = swjgCd;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getRunSta() {
        return runSta;
    }

    public void setRunSta(String runSta) {
        this.runSta = runSta;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public BigDecimal getLgtd() {
        return lgtd;
    }

    public void setLgtd(BigDecimal lgtd) {
        this.lgtd = lgtd;
    }

    public BigDecimal getLttd() {
        return lttd;
    }

    public void setLttd(BigDecimal lttd) {
        this.lttd = lttd;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "WrWiuB{" +
        ", uuid=" + uuid +
        ", djxh=" + djxh +
        ", nsrsbh=" + nsrsbh +
        ", wiuNm=" + wiuNm +
        ", lrNm=" + lrNm +
        ", lawAtt=" + lawAtt +
        ", userTp=" + userTp +
        ", tradTp=" + tradTp +
        ", isHiLev=" + isHiLev +
        ", ecoTp=" + ecoTp +
        ", monG=" + monG +
        ", wiuTp=" + wiuTp +
        ", yrWu=" + yrWu +
        ", certSta=" + certSta +
        ", isVimp=" + isVimp +
        ", adlCd=" + adlCd +
        ", sljgCd=" + sljgCd +
        ", swjgCd=" + swjgCd +
        ", web=" + web +
        ", eMail=" + eMail +
        ", addr=" + addr +
        ", zip=" + zip +
        ", tel=" + tel +
        ", fax=" + fax +
        ", isuse=" + isuse +
        ", runSta=" + runSta +
        ", ts=" + ts +
        ", lgtd=" + lgtd +
        ", lttd=" + lttd +
        ", nt=" + nt +
        "}";
    }
}
