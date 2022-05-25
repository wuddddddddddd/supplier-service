package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBasicInfoMapper;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.po.SupplierDictPo;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.models.request.SupplierDictRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 供应商基础信息数据仓储层实现.
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

    @Override
    public int updateByOneParams(SFunction<SupplierBasicInfoPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierBasicInfoPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<SupplierBasicInfoPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierBasicInfoMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<SupplierPageResponse> getCustomerPage(Page<SupplierBasicInfoPo> page, SupplierPageRequest request) {
        return supplierBasicInfoMapper.getCustomerPage(page, request);
    }
}
