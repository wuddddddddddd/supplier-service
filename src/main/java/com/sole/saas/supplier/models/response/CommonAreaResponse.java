package com.sole.saas.supplier.models.response;

/**
 * @author wjd
 * @description
 * @date 2022-05-25
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wjd
 * @description 区域信息响应实体.
 * @date 2022-05-25
 */
@ApiModel(value="区域信息响应实体")
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonAreaResponse implements Serializable {
    private static final long serialVersionUID = 5008163606234723463L;


    @ApiModelProperty(value = "主键ID")
    private Integer areaId;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "地区类型")
    private String areaType;

    @ApiModelProperty(value = "父ID")
    private Integer areaParentId;

    @ApiModelProperty(value = "地区英文名称")
    private String areaEName;

    @ApiModelProperty(value = "备用")
    private String tempOther;

    @ApiModelProperty(value = "状态")
    private Integer statusFlag;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "区号")
    private Integer quHao;

    @ApiModelProperty(value = "邮编")
    private Integer youBian;

    @ApiModelProperty(value = "主键ID")
    private Boolean isWebSite;

    @ApiModelProperty(value = "行政大区")
    private String district;

}
