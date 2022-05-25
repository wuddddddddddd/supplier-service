package com.sole.saas.supplier.models.request;

import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@ApiModel(value="初始化创建供应商请求体")
@Data
public class SupplierRequest implements Serializable {
    private static final long serialVersionUID = 3055831052010680307L;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商基础信息")
    private SupplierBasicInfoRequest basicInfoRequest;

    @ApiModelProperty(value = "资质信息")
    private QualificationInfoRequest qualificationInfoRequest;

    @ApiModelProperty(value = "注册信息")
    private RegisterInfoRequest registerInfoRequest;

    @ApiModelProperty(value = "联系人信息")
    private SupplierUserInfoRequest supplierUserInfoRequest;

    @ApiModelProperty(value = "所属采购员信息")
    private SupplierBuyerUserRequest supplierBuyerUserRequest = new SupplierBuyerUserRequest();

    @ApiModelProperty(value = "主营行业")
    private List<SupplierIndustryRequest> industryRequestList = new ArrayList<>();
}
