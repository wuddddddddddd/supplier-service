package com.sole.saas.supplier.models.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wjd
 * @description 供应商主营行业关联关系请求实体.
 * @date 2022-05-19
 */
@ApiModel(value="供应商主营行业关联关系请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierIndustryRequest implements Serializable {
    private static final long serialVersionUID = -1058646209617906408L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "主营行业ID")
    private Long industryId;

    @ApiModelProperty(value = "主营行业名称")
    private String industryName;

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

    @ApiModelProperty(value = "供应商ID集")
    private List<Long> supplierIdList;


}
