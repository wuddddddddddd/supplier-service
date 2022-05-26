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

    STOP_COOPERATION(6, "终止合作", "supplier", "供应商"),

    BLACK(7, "加黑", "supplier", "供应商"),

    NOT_DEAL(8, "未处理", "complaintInfo", "投诉"),

    DEAL_DONE(9, "已处理", "complaintInfo", "投诉"),

    NO_COOPERATION(10, "无协议", "contract", "合同"),

    NOT_ACTIVE(11, "未生效", "contract", "合同"),

    IN_ACTIVE(12, "已生效", "contract,industryQualification", "合同,行业资质"),

    EXPIRE(13, "已过期", "contract,industryQualification", "合同,行业资质");



    private int code;

    private String name;

    private String functionCode;

    private String functionName;
}
