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

    CREATE_ING(1, "创建待完善", "supplier", "供应商"),

    DRAFT(2, "草稿", "common", "供应商"),

    IN_PROCESS(3, "审批中", "common", "通用"),

    PROCESS_REJECT(4, "审批驳回", "common", "通用" ),

    PROCESS_SUCCESS(5, "审批通过", "common", "通用"),

    STANDARD(6, "合格", "supplier", "供应商"),

    STOP_COOPERATION(7, "终止合作", "supplier", "供应商"),

    BLACK(8, "加黑", "supplier", "供应商"),

    NOT_DEAL(9, "未处理", "complaintInfo", "投诉"),

    DEAL_DONE(10, "已处理", "complaintInfo", "投诉"),

    NO_COOPERATION(11, "无协议", "contract", "合同"),

    NOT_ACTIVE(12, "未生效", "contract", "合同"),

    IN_ACTIVE(13, "已生效", "contract,industryQualification", "合同,行业资质"),

    EXPIRE(14, "已过期", "contract,industryQualification", "合同,行业资质");



    private final int code;

    private final String name;

    private final String functionCode;

    private final String functionName;
}
