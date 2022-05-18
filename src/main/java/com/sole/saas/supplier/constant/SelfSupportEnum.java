package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description 自营状态枚举值.
 * @date 2022-05-16
 */
@Getter
@AllArgsConstructor
public enum SelfSupportEnum {

    OWNER(1, "自营"),

    OTHER(2, "非自营");

    private int code;

    private String name;
}
