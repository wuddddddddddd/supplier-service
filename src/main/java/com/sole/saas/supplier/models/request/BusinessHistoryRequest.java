package com.sole.saas.supplier.models.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjd
 * @description 供应商业务状态变更记录请求体.
 * @date 2022-05-18
 */
@ApiModel(value="供应商业务状态变更记录请求体")
@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessHistoryRequest implements Serializable {
    private static final long serialVersionUID = 4151261832022689340L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "所属类型")
    private Integer type;

    @ApiModelProperty(value = "业务主键ID")
    private Long businessId;

    @ApiModelProperty(value = "业务状态")
    private Integer businessStatus;

    @ApiModelProperty(value = "变更前业务状态")
    private Integer oldBusinessStatus;

    @ApiModelProperty(value = "原因")
    private String remark;

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
