package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description 历史业务类型枚举值.
 * @date 2022-05-16
 */
@Getter
@AllArgsConstructor
public enum HistoryTypeEnum {

    SUPPLIER(1, "供应商");

    private int code;

    private String name;
}
