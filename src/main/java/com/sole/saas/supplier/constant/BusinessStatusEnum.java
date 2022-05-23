package com.sole.saas.supplier.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjd
 * @description 业务状态枚举值.
 * @date 2022-05-16
 */
@Getter
@AllArgsConstructor
public enum BusinessStatusEnum {

    CREATE_ING(1, "创建待完善"),

    DRAFT(2, "草稿"),

    IN_PROCESS(3, "审批中"),

    PROCESS_REJECT(4, "审批驳回"),

    PROCESS_SUCCESS(5, "审批通过"),

    STOP_COOPERATION(6, "终止合作"),

    BLACK(7, "加黑"),

    NOT_DEAL(8, "未处理"),

    DEAL_DONE(9, "已处理");

    private int code;

    private String name;
}
