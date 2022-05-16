package com.sole.saas.supplier.models.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@ApiModel(value="供应商所属采购员信息数据库映射实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierBuyerUserRequest implements Serializable {

    private static final long serialVersionUID = -9220781524694540403L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "所属采购员ID")
    private Long buyerUserId;

    @ApiModelProperty(value = "原所属采购员ID")
    private Long oldBuyerUserId;

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
