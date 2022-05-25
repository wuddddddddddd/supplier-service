package com.sole.saas.supplier.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wjd
 * @description 初始化创建供应商请求体.
 * @date 2022-05-16
 */
@ApiModel(value="初始化创建供应商请求体")
@Data
@ToString
public class InitSupplierRequest implements Serializable {
    private static final long serialVersionUID = -6586896197277884368L;


    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "联系人")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userTelephone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "所属采购员ID")
    private Long buyerUserId;

    @ApiModelProperty(value = "信息来源")
    private Integer source;
}
