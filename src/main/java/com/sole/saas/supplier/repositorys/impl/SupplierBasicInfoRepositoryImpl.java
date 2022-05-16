package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBasicInfoMapper;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
@Repository
public class SupplierBasicInfoRepositoryImpl extends ServiceImpl<SupplierBasicInfoMapper, SupplierBasicInfoPo> implements ISupplierBasicInfoRepository {

    private final SupplierBasicInfoMapper supplierBasicInfoMapper;

    public SupplierBasicInfoRepositoryImpl(SupplierBasicInfoMapper supplierBasicInfoMapper) {
        this.supplierBasicInfoMapper = supplierBasicInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBasicInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(SupplierBasicInfoPo entity) {
        return super.updateById(entity);
    }
}
