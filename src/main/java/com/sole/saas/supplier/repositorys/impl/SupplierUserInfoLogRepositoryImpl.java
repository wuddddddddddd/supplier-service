package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierUserInfoLogMapper;
import com.sole.saas.supplier.models.po.SupplierUserInfoLogPo;
import com.sole.saas.supplier.repositorys.ISupplierUserInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class SupplierUserInfoLogRepositoryImpl extends ServiceImpl<SupplierUserInfoLogMapper, SupplierUserInfoLogPo> implements ISupplierUserInfoLogRepository {

   private final SupplierUserInfoLogMapper supplierUserInfoLogMapper;

    public SupplierUserInfoLogRepositoryImpl(SupplierUserInfoLogMapper supplierUserInfoLogMapper) {
        this.supplierUserInfoLogMapper = supplierUserInfoLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierUserInfoLogPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierUserInfoLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierUserInfoLogPo, ?> conditionColumn, Object conditionValue) {
        return 0;
    }
}
