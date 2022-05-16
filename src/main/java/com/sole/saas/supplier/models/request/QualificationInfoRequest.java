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
 * @description 供应商资质信息请求实体.
 * @date 2022-05-16
 */
@ApiModel(value="供应商资质信息请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class QualificationInfoRequest implements Serializable {

    private static final long serialVersionUID = 6122719179977341569L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "营业执照是否长期")
    private Boolean isLongTime;

    @ApiModelProperty(value = "营业执照有效期至")
    private Date businessLicenseDate;

    @ApiModelProperty(value = "法人名称")
    private String legalPersonName;

    @ApiModelProperty(value = "法人证件类型对应业务字典ID")
    private Long legalPersonCertificateId;

    @ApiModelProperty(value = "法人证件号码")
    private String legalPersonNumber;

    @ApiModelProperty(value = "开户银行")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankNumber;

    @ApiModelProperty(value = "是否为一般纳税人")
    private Boolean isGeneralTaxpayer;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerNumber;

    @ApiModelProperty(value = "是否三证合一")
    private Boolean isThreeInOne;

    @ApiModelProperty(value = "一般纳税人资格证书url")
    private String generalTaxpayerUrl;

    @ApiModelProperty(value = "营业执照电子版url")
    private String businessLicenseUrl;

    @ApiModelProperty(value = "税务登记证url")
    private String taxRegistrationUrl;

    @ApiModelProperty(value = "开户行信息电子版url")
    private String bankInfoUrl;

    @ApiModelProperty(value = "组织机构代码证url")
    private String organizationCodeUrl;

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
