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
 * @description 供应商业务字典关联关系响应实体.
 * @date 2022-05-17
 */
@ApiModel(value="供应商业务字典关联关系响应实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SupplierDictResponse implements Serializable {

    private static final long serialVersionUID = 2606727223687972850L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "编码(作用域)")
    private String code;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "业务字典ID")
    private Long dictId;

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
