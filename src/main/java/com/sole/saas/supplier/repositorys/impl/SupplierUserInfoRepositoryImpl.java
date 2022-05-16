package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierUserInfoMapper;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.repositorys.ISupplierUserInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class SupplierUserInfoRepositoryImpl extends ServiceImpl<SupplierUserInfoMapper, SupplierUserInfoPo> implements ISupplierUserInfoRepository {

    private final SupplierUserInfoMapper supplierUserInfoMapper;

    public SupplierUserInfoRepositoryImpl(SupplierUserInfoMapper supplierUserInfoMapper) {
        this.supplierUserInfoMapper = supplierUserInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierUserInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(SupplierUserInfoPo entity) {
        return super.updateById(entity);
    }
}
