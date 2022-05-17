package com.sole.saas.supplier.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wjd
 * @description 初始化创建供应商响应实体.
 * @date 2022-05-16
 */
@ApiModel(value="初始化创建供应商响应实体")
@Data
public class SupplierResponse implements Serializable {

    private static final long serialVersionUID = -5488388052924058334L;


    @ApiModelProperty(value = "供应商基础信息")
    private SupplierBasicInfoResponse basicInfoResponse;

    @ApiModelProperty(value = "资质信息")
    private QualificationInfoResponse qualificationInfoResponse;

    @ApiModelProperty(value = "注册信息")
    private RegisterInfoResponse registerInfoResponse;

    @ApiModelProperty(value = "联系人信息")
    private SupplierUserInfoResponse supplierUserInfoResponse;

    @ApiModelProperty(value = "所属采购员信息")
    private SupplierBuyerUserResponse supplierBuyerUserResponse;
}
