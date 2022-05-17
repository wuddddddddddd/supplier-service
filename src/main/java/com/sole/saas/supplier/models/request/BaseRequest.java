package com.sole.saas.supplier.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wjd
 * @description 请求信息基类.
 * @date 2022-05-12
 */
@ApiModel(value="请求信息基类")
@Data
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    private int pageIndex = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 10;
}
