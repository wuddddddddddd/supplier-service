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
public enum SupplierDictCodeEnum {
    INDUSTRY("INDUSTRY", "主营行业");


    private String code;

    private String name;

}
