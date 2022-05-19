package com.sole.saas.supplier.models.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 供应商基础信息请求实体.
 * @Author wjd
 * @date 2022/5/9
 */
@ApiModel(value="供应商基础信息请求实体")
@Data
public class SupplierBasicInfoRequest implements Serializable {

    private static final long serialVersionUID = 3749806163591402191L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "当为记录请求时对应的供应商主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "来源")
    private Integer source;

    @ApiModelProperty(value = "供应商编码")
    private String code;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "供应商简称")
    private String shortName;

    @ApiModelProperty(value = "公司规模对应业务字典ID")
    private Long supplierSizeId;

    @ApiModelProperty(value = "经营类型对应业务字典ID")
    private Long manageTypeId;

    @ApiModelProperty(value = "公司性质对应业务字典ID")
    private Long companyNatureId;

    @ApiModelProperty(value = "每年销售额(万元)")
    private BigDecimal salesYear;

    @ApiModelProperty(value = "经营范围")
    private String businessScope;

    @ApiModelProperty(value = "公司简介")
    private String description;

    @ApiModelProperty(value = "自营状态")
    private Integer selfSupportType;

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
