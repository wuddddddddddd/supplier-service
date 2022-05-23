package com.sole.saas.supplier.models.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wjd
 * @description 线下业绩信息请求实体.
 * @date 2022-05-21
 */
@ApiModel(value="线下业绩信息请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class OfflineAchievementResponse implements Serializable {
    private static final long serialVersionUID = 5567201301971294801L;


    @ApiModelProperty(value = "主键ID")
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
