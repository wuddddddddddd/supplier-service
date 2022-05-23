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
 * @description 供应商合同信息请求实体.
 * @date 2022-05-23
 */
@ApiModel(value="供应商合同信息请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class ContractInfoResponse implements Serializable {
    private static final long serialVersionUID = -6115295140162854772L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "合同名称")
    private String name;

    @ApiModelProperty(value = "生效日期")
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "截至日期")
    @JSONField(format="yyyy-MM-dd")
    private Date endData;

    @ApiModelProperty(value = "起签日期")
    @JSONField(format="yyyy-MM-dd")
    private Date startSignDate;

    @ApiModelProperty(value = "签章日期")
    @JSONField(format="yyyy-MM-dd")
    private Date signatureDate;

    @ApiModelProperty(value = "签章人")
    private String signatureUser;

    @ApiModelProperty(value = "上传合同附件")
    private String uploadFileUrl;

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
