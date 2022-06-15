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
 * @description 序列号信息请求实体.
 * @date 2022-06-06
 */
@ApiModel(value="序列号信息请求实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class SequenceRequest implements Serializable {
    private static final long serialVersionUID = 5155434482333517436L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "序列名称")
    private String seq_name;

    @ApiModelProperty(value = "当前值")
    private Integer currentVal;

    @ApiModelProperty(value = "步长")
    private Integer incrementVal;

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
