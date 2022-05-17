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

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@ApiModel(value="供应商基础信息数据库映射实体")
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("supplier_register_info")
public class RegisterInfoPo extends Model<RegisterInfoPo> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "供应商基础信息主键ID")
    private Long supplierId;

    @ApiModelProperty(value = "注册资金(万元)")
    private BigDecimal registeredCapital;

    @ApiModelProperty(value = "公司注册成立日期")
    private Date registeredDate;

    @ApiModelProperty(value = "注册地区id(省)")
    private Long provinceId;

    @ApiModelProperty(value = "注册地区ID(市)")
    private Long cityId;

    @ApiModelProperty(value = "注册地区ID(区)")
    private Long districtId;

    @ApiModelProperty(value = "详细注册地址")
    private String registeredAddress;

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
