package com.sole.saas.supplier.models.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjd
 * @description 供应商审批记录信息请求实体.
 * @date 2022-05-17
 */
@ApiModel(value="供应商审批记录信息请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class CheckOpinionResponse implements Serializable {
    private static final long serialVersionUID = 7310578381504601700L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "业务主键ID")
    private Long businessId;

    @ApiModelProperty(value = "审批人ID")
    private Long assigneeId;

    @ApiModelProperty(value = "审批方式")
    private Integer opinionType;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "备注")
    private String remark;

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
