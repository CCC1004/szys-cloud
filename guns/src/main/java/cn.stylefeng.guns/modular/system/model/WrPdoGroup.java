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
 * 水源分组表
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
@TableName("wr_pdo_group")
public class WrPdoGroup extends Model<WrPdoGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 水源分组代码
     */
    @TableId("UUID")
    private String uuid;
    /**
     * 序号
     */
    @TableField("SN")
    private BigDecimal sn;
    /**
     * 水源分组名称
     */
    @TableField("GROUP_NM")
    private String groupNm;
    /**
     * 取水口许可证编码
     */
    @TableField("WPC_CD")
    private String wpcCd;
    /**
     * 取水许可证号
     */
    @TableField("WPC_SN")
    private String wpcSn;
    /**
     * 取水许可证开始日期
     */
    @TableField("INT_BD")
    private Date intBd;
    /**
     * 取水许可证结束日期
     */
    @TableField("INT_DL")
    private Date intDl;
    /**
     * 取水用户编码
     */
    @TableField("WIU_CD")
    private String wiuCd;
    /**
     * 水源类型
     */
    @TableField("SOUR_TP")
    private String sourTp;
    /**
     * 取水用途
     */
    @TableField("INT_USE")
    private String intUse;
    /**
     * 取水行业
     */
    @TableField("TRAD_TP")
    private String tradTp;
    /**
     * 地下水超采区类型
     */
    @TableField("GWOA_TP")
    private String gwoaTp;
    /**
     * 管网覆盖
     */
    @TableField("IS_PIPE")
    private String isPipe;
    /**
     * 税额等次
     */
    @TableField("WRTR_TP")
    private String wrtrTp;
    /**
     * 取水口所在行政分区
     */
    @TableField("AD_CD")
    private String adCd;
    /**
     * 取水口所在水资源分区
     */
    @TableField("WRZ_CD")
    private String wrzCd;
    /**
     * 所在地
     */
    @TableField("ADDR")
    private String addr;
    /**
     * 取水方式
     */
    @TableField("INT_TP")
    private String intTp;
    /**
     * 地下水类型（深层地下水和浅层地下水）
     */
    @TableField("GRO_TP")
    private String groTp;
    /**
     * 经度
     */
    @TableField("LGTD")
    private String lgtd;
    /**
     * 纬度
     */
    @TableField("LTTD")
    private String lttd;
    /**
     * 水源状态
     */
    @TableField("STATE")
    private String state;
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

    public BigDecimal getSn() {
        return sn;
    }

    public void setSn(BigDecimal sn) {
        this.sn = sn;
    }

    public String getGroupNm() {
        return groupNm;
    }

    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

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

    public String getWiuCd() {
        return wiuCd;
    }

    public void setWiuCd(String wiuCd) {
        this.wiuCd = wiuCd;
    }

    public String getSourTp() {
        return sourTp;
    }

    public void setSourTp(String sourTp) {
        this.sourTp = sourTp;
    }

    public String getIntUse() {
        return intUse;
    }

    public void setIntUse(String intUse) {
        this.intUse = intUse;
    }

    public String getTradTp() {
        return tradTp;
    }

    public void setTradTp(String tradTp) {
        this.tradTp = tradTp;
    }

    public String getGwoaTp() {
        return gwoaTp;
    }

    public void setGwoaTp(String gwoaTp) {
        this.gwoaTp = gwoaTp;
    }

    public String getIsPipe() {
        return isPipe;
    }

    public void setIsPipe(String isPipe) {
        this.isPipe = isPipe;
    }

    public String getWrtrTp() {
        return wrtrTp;
    }

    public void setWrtrTp(String wrtrTp) {
        this.wrtrTp = wrtrTp;
    }

    public String getAdCd() {
        return adCd;
    }

    public void setAdCd(String adCd) {
        this.adCd = adCd;
    }

    public String getWrzCd() {
        return wrzCd;
    }

    public void setWrzCd(String wrzCd) {
        this.wrzCd = wrzCd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIntTp() {
        return intTp;
    }

    public void setIntTp(String intTp) {
        this.intTp = intTp;
    }

    public String getGroTp() {
        return groTp;
    }

    public void setGroTp(String groTp) {
        this.groTp = groTp;
    }

    public String getLgtd() {
        return lgtd;
    }

    public void setLgtd(String lgtd) {
        this.lgtd = lgtd;
    }

    public String getLttd() {
        return lttd;
    }

    public void setLttd(String lttd) {
        this.lttd = lttd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "WrPdoGroup{" +
        ", uuid=" + uuid +
        ", sn=" + sn +
        ", groupNm=" + groupNm +
        ", wpcCd=" + wpcCd +
        ", wpcSn=" + wpcSn +
        ", intBd=" + intBd +
        ", intDl=" + intDl +
        ", wiuCd=" + wiuCd +
        ", sourTp=" + sourTp +
        ", intUse=" + intUse +
        ", tradTp=" + tradTp +
        ", gwoaTp=" + gwoaTp +
        ", isPipe=" + isPipe +
        ", wrtrTp=" + wrtrTp +
        ", adCd=" + adCd +
        ", wrzCd=" + wrzCd +
        ", addr=" + addr +
        ", intTp=" + intTp +
        ", groTp=" + groTp +
        ", lgtd=" + lgtd +
        ", lttd=" + lttd +
        ", state=" + state +
        ", nt=" + nt +
        "}";
    }
}
