package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description 重置条件类型枚举值.
 * @date 2022-06-02
 */
@Getter
@AllArgsConstructor
public enum ResetConditionTypeEnum {

    NORMAL_DATE(1, "普通日期年月日"),

    YEAR(2, "年"),

    MONTH(3, "月"),

    DAY(4, "日");


    private final int code;

    private final String name;
}
