package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBuyerUserLogPo;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface ISupplierBuyerUserLogRepository extends IService<SupplierBuyerUserLogPo> {

    int updateByOneParams(SFunction<SupplierBuyerUserLogPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierBuyerUserLogPo, ?> conditionColumn, Object conditionValue);
}
