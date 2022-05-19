package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBuyerUserLogMapper;
import com.sole.saas.supplier.models.po.SupplierBuyerUserLogPo;
import com.sole.saas.supplier.repositorys.ISupplierBuyerUserLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class SupplierBuyerUserLogRepositoryImpl extends ServiceImpl<SupplierBuyerUserLogMapper, SupplierBuyerUserLogPo> implements ISupplierBuyerUserLogRepository {

    private final SupplierBuyerUserLogMapper supplierBuyerUserLogMapper;

    public SupplierBuyerUserLogRepositoryImpl(SupplierBuyerUserLogMapper supplierBuyerUserLogMapper) {
        this.supplierBuyerUserLogMapper = supplierBuyerUserLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBuyerUserLogPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierBuyerUserLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierBuyerUserLogPo, ?> conditionColumn, Object conditionValue) {
        return 0;
    }
}
