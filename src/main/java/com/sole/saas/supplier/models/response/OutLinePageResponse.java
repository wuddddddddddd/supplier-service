package com.sole.saas.supplier.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wjd
 * @description 供应商违规信息分页响应体.
 * @date 2022-05-26
 */
@ApiModel(value="供应商违规信息分页响应体")
@Data
@EqualsAndHashCode(callSuper=false)
public class OutLinePageResponse implements Serializable {
    private static final long serialVersionUID = -2668619981380946714L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "记录表对应的供应商主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "违规数量")
    private Integer outLineNumber;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    @ApiModelProperty(value = "经营类型ID")
    private Long manageTypeId;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "成立时间")
    private Date registeredDate;

    @ApiModelProperty(value = "注册地区id(省)")
    private Long provinceId;

    @ApiModelProperty(value = "注册地区ID(市)")
    private Long cityId;

    @ApiModelProperty(value = "注册地区ID(区)")
    private Long districtId;

    @ApiModelProperty(value = "联系人")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userTelephone;

    @ApiModelProperty(value = "所属采购员ID")
    private Long buyerUserId;

    @ApiModelProperty(value = "经营类型名称")
    private String manageTypeName;

    @ApiModelProperty(value = "注册地区(省)名称")
    private String provinceName;

    @ApiModelProperty(value = "注册地区(市)名称")
    private String cityName;

    @ApiModelProperty(value = "注册地区ID(区)名称")
    private String districtName;

    @ApiModelProperty(value = "所属采购员名称")
    private String buyerUserName;

    @ApiModelProperty(value = "主营行业名称拼接")
    private String industryNames;

    @ApiModelProperty(value = "合同授权协议业务状态")
    private Integer contractBusinessStatus;

    @ApiModelProperty(value = "合同授权协议业务状态名称")
    private Integer contractBusinessStatusName;

    @ApiModelProperty(value = "采购数量")
    private Integer purchaseNumber;

    @ApiModelProperty(value = "采购金额")
    private BigDecimal purchaseAmount;
}
