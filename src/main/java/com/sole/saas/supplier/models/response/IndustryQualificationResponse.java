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
 * @description 行业资质信息响应体.
 * @date 2022-05-21
 */
@ApiModel(value="行业资质信息响应体")
@Data
@EqualsAndHashCode(callSuper=false)
public class IndustryQualificationResponse implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "资质类型ID")
    private Long typeId;

    @ApiModelProperty(value = "资质证件")
    private String url;

    @ApiModelProperty(value = "资质有效期开始")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "资质有效期结束")
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;

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
