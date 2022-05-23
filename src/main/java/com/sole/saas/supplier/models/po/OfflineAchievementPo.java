package com.sole.saas.supplier.models.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wjd
 * @description 线下业绩信息数据库映射实体.
 * @date 2022-05-21
 */
@ApiModel(value="线下业绩信息数据库映射实体")
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("offline_achievement")
public class OfflineAchievementPo extends Model<OfflineAchievementPo> {
    private static final long serialVersionUID = 2680345734600215163L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "工程名称")
    private String projectName;

    @ApiModelProperty(value = "工程地址")
    private String projectAddress;

    @ApiModelProperty(value = "合作单位")
    private String cooperationUnit;

    @ApiModelProperty(value = "合同金额")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "合作周期开始时间")
    @JSONField(format="yyyy-MM-dd")
    private Date cooperationStartDate;

    @ApiModelProperty(value = "合作周期结束时间")
    @JSONField(format="yyyy-MM-dd")
    private Date cooperationEndDate;

    @ApiModelProperty(value = "联系人")
    private String userName;

    @ApiModelProperty(value = "联系电话")
    private String userPhone;

    @ApiModelProperty(value = "上传文件路径")
    private String fileUrl;

    @ApiModelProperty(value = "业务状态")
    private Integer businessStatus;

    @ApiModelProperty(value = "创建人")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Long updateUserId;

    @ApiModelProperty(value = "修改时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
