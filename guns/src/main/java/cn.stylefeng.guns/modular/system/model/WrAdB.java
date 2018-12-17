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
 * 行政区划表
 * </p>
 *
 * @author superc
 * @since 2018-11-21
 */
@TableName("wr_ad_b")
public class WrAdB extends Model<WrAdB> {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区划代码
     */
    @TableId("AD_CD")
    private String adCd;
    /**
     * 行政区划名称
     */
    @TableField("AD_NM")
    private String adNm;
    /**
     * 行政区划简称
     */
    @TableField("AD_SHNM")
    private String adShnm;
    /**
     * 行政区划面积
     */
    @TableField("AD_A")
    private BigDecimal adA;
    /**
     * 行政区划是否启用
     */
    @TableField("IS_ENABLE")
    private String isEnable;
    /**
     * 时间戳
     */
    @TableField("TS")
    private Date ts;
    /**
     * 备注
     */
    @TableField("NT")
    private String nt;


    public String getAdCd() {
        return adCd;
    }

    public void setAdCd(String adCd) {
        this.adCd = adCd;
    }

    public String getAdNm() {
        return adNm;
    }

    public void setAdNm(String adNm) {
        this.adNm = adNm;
    }

    public String getAdShnm() {
        return adShnm;
    }

    public void setAdShnm(String adShnm) {
        this.adShnm = adShnm;
    }

    public BigDecimal getAdA() {
        return adA;
    }

    public void setAdA(BigDecimal adA) {
        this.adA = adA;
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

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    protected Serializable pkVal() {
        return this.adCd;
    }

    @Override
    public String toString() {
        return "WrAdB{" +
                "adCd='" + adCd + '\'' +
                ", adNm='" + adNm + '\'' +
                ", adShnm='" + adShnm + '\'' +
                ", adA=" + adA +
                ", isEnable='" + isEnable + '\'' +
                ", ts=" + ts +
                ", nt='" + nt + '\'' +
                '}';
    }
}
