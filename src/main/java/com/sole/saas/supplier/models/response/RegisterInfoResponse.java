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
 * @description 供应商注册信息响应实体.
 * @date 2022-05-16
 */
@ApiModel(value="供应商注册信息响应实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class RegisterInfoResponse implements Serializable {

    private static final long serialVersionUID = 8648029877085351667L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "注册资金(万元)")
    private BigDecimal registered_capital;

    @ApiModelProperty(value = "公司注册成立日期")
    private Date registered_date;

    @ApiModelProperty(value = "注册地区id(省)")
    private Long provinceId;

    @ApiModelProperty(value = "注册地区ID(市)")
    private Long cityId;

    @ApiModelProperty(value = "注册地区ID(区)")
    private Long districtId;

    @ApiModelProperty(value = "详细注册地址")
    private String registeredAddress;

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
