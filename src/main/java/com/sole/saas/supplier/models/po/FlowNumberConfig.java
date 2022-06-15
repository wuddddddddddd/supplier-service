package com.sole.saas.supplier.models.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wjd
 * @description 单据生成规则配置信息数据库映射实体.
 * @date 2022-06-06
 */
@ApiModel(value="单据生成规则配置信息数据库映射实体")
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("user_flow_number_config")
public class FlowNumberConfig extends Model<FlowNumberConfig> {
    private static final long serialVersionUID = -8635783137379122199L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "序列ID")
    private Long seq_id;

    @ApiModelProperty(value = "单据类型")
    private String flowNumberType;

    @ApiModelProperty(value = "单据生成规则")
    private String flowNumberRule;

    @ApiModelProperty(value = "初始化单据号")
    private String initFlowNumber;

    @ApiModelProperty(value = "是否重置序列号")
    private Boolean isReset;

    @ApiModelProperty(value = "重置条件")
    private Integer resetCondition;

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
}
