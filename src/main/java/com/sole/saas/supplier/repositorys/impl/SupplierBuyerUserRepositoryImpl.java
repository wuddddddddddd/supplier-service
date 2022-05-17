package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBuyerUserMapper;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
import com.sole.saas.supplier.repositorys.ISupplierBuyerUserRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class SupplierBuyerUserRepositoryImpl extends ServiceImpl<SupplierBuyerUserMapper, SupplierBuyerUserPo> implements ISupplierBuyerUserRepository {

    private final SupplierBuyerUserMapper supplierBuyerUserMapper;

    public SupplierBuyerUserRepositoryImpl(SupplierBuyerUserMapper supplierBuyerUserMapper) {
        this.supplierBuyerUserMapper = supplierBuyerUserMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBuyerUserPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(SupplierBuyerUserPo entity) {
        return super.updateById(entity);
    }

    @Override
    public SupplierBuyerUserPo getByParams(SupplierBuyerUserRequest request) {
        final LambdaQueryWrapper<SupplierBuyerUserPo> queryWrapper = this.getQueryWrapper(request);
        return supplierBuyerUserMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierBuyerUserPo> getQueryWrapper(SupplierBuyerUserRequest request) {
        LambdaQueryWrapper<SupplierBuyerUserPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierBuyerUserPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierBuyerUserPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierBuyerUserPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
