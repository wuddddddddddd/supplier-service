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
 * @description
 * @date 2022-05-17
 */
@ApiModel(value="供应商分页信息响应实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierPageResponse implements Serializable {
    private static final long serialVersionUID = -50528329626207437L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商编码")
    private Long supplierId;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "公司性质对应业务字典ID")
    private Long companyNatureId;

    @ApiModelProperty(value = "来源")
    private Integer source;

    @ApiModelProperty(value = "经营类型对应业务字典ID")
    private Long manageTypeId;

    @ApiModelProperty(value = "自营类型")
    private Integer selfSupportType;

    @ApiModelProperty(value = "业务状态")
    private Integer businessStatus;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "信用评级")
    private String creditLevel;

    @ApiModelProperty(value = "信用得分")
    private String creditScore;


    @ApiModelProperty(value = "账号")
    private String userAccount;

    @ApiModelProperty(value = "联系人")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userTelephone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;


    @ApiModelProperty(value = "公司注册成立日期")
    private Date registeredDate;

    @ApiModelProperty(value = "注册地区id(省)")
    private Long provinceId;

    @ApiModelProperty(value = "注册地区ID(市)")
    private Long cityId;

    @ApiModelProperty(value = "注册地区ID(区)")
    private Long districtId;

    @ApiModelProperty(value = "所属采购员ID")
    private Long buyerUserId;

    @ApiModelProperty(value = "原所属采购员ID")
    private Long oldBuyerUserId;


    @ApiModelProperty(value = "合同授权协议业务状态")
    private Integer contractBusinessStatus;

    @ApiModelProperty(value = "合同授权协议业务状态名称")
    private Integer contractBusinessStatusName;

    @ApiModelProperty(value = "行业资质待审")
    private Integer industryQualificationInProcess;

    @ApiModelProperty(value = "行业资质已审")
    private Integer industryQualificationApproval;


    @ApiModelProperty(value = "经营类型对应业务字典ID")
    private String manageTypeName;

    @ApiModelProperty(value = "注册地区(省)名称")
    private String provinceName;

    @ApiModelProperty(value = "注册地区(市)名称")
    private String cityName;

    @ApiModelProperty(value = "注册地区ID(区)名称")
    private String districtName;

    @ApiModelProperty(value = "所属采购员名称")
    private String buyerUserName;

    @ApiModelProperty(value = "原所属采购员名称")
    private String oldBuyerUserName;

    @ApiModelProperty(value = "主营行业名称拼接")
    private String industryNames;

}
