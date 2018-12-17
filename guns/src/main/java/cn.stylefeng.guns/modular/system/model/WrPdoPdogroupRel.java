package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 水源分组与取水口信息中间关系表
 * </p>
 *
 * @author ll
 * @since 2018-11-26
 */
@TableName("wr_pdo_pdogroup_rel")
public class WrPdoPdogroupRel extends Model<WrPdoPdogroupRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 水源分组编码
     */
    @TableId("PDO_GROUP_ID")
    private String pdoGroupId;
    /**
     * 取水口编码
     */
    @TableField("PDO_ID")
    private String pdoId;
    /**
     * 状态（1:使用；2:弃用）
     */
    @TableField("STATUS")
    private String status;
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


    public String getPdoGroupId() {
        return pdoGroupId;
    }

    public void setPdoGroupId(String pdoGroupId) {
        this.pdoGroupId = pdoGroupId;
    }

    public String getPdoId() {
        return pdoId;
    }

    public void setPdoId(String pdoId) {
        this.pdoId = pdoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return this.pdoGroupId;
    }

    @Override
    public String toString() {
        return "WrPdoPdogroupRel{" +
        ", pdoGroupId=" + pdoGroupId +
        ", pdoId=" + pdoId +
        ", status=" + status +
        ", ts=" + ts +
        ", nt=" + nt +
        "}";
    }
}
