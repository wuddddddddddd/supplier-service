package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierUserInfoLogPo;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface ISupplierUserInfoLogRepository extends IService<SupplierUserInfoLogPo> {

    int updateByOneParams(SFunction<SupplierUserInfoLogPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierUserInfoLogPo, ?> conditionColumn, Object conditionValue);
}
