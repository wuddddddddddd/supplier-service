package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description 审批信息类型枚举值.
 * @date 2022-05-23
 */
@Getter
@AllArgsConstructor
public enum OpinionTypeEnum {

    SUPPLIER(1, "供应商"),

    INDUSTRY_QUALIFICATION(2, "行业资质"),

    OFFLINE_ACHIEVEMENT(3, "线下业绩");

    private int code;

    private String name;
}
