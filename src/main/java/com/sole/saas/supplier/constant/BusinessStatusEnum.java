package com.sole.saas.supplier.constant;

import lombok.Getter;

/**
 * @author wjd
 * @description 业务状态枚举值.
 * @date 2022-05-16
 */
@Getter
public enum BusinessStatusEnum {

    CREATE_ING(1, "创建待完善"),

    DRAFT(2, "草稿"),

    IN_PROCESS(3, "审批中");

    private int code;

    private String name;

    BusinessStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
