package com.sole.saas.supplier.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wjd
 * @description 供应商联系人信息响应实体.
 * @date 2022-05-16
 */
@ApiModel(value="供应商联系人信息响应实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierUserInfoResponse implements Serializable {

    private static final long serialVersionUID = -5693356269450569686L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改人")
    private String updateUserId;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "状态")
    private String status;
}
