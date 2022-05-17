package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {

    MALL_REGISTER(1, "商城注册"),

    SYSTEM_CREATE(2, "后台创建");

    private int code;

    private String name;
}
