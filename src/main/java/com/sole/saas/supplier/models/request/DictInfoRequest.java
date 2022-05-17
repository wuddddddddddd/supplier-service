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
 * @description 业务字典信息数据库映射实体.
 * @date 2022-05-17
 */
@ApiModel(value="业务字典信息数据库映射实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class DictInfoRequest implements Serializable {
    private static final long serialVersionUID = -5182321771848699301L;


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "业务名称")
    private String name;

    @ApiModelProperty(value = "唯一编码")
    private String code;

    @ApiModelProperty(value = "备注")
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

    @ApiModelProperty(value = "主键ID集")
    private List<Long> idList;
}
