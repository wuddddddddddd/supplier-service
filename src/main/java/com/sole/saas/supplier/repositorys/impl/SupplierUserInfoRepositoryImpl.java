package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierUserInfoMapper;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;
import com.sole.saas.supplier.repositorys.ISupplierUserInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 供应商联系人信息数据仓储层实现.
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

    @Override
    public SupplierUserInfoPo getByParams(SupplierUserInfoRequest request) {
        final LambdaQueryWrapper<SupplierUserInfoPo> queryWrapper = this.getQueryWrapper(request);
        return supplierUserInfoMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierUserInfoPo> getQueryWrapper(SupplierUserInfoRequest request) {
        LambdaQueryWrapper<SupplierUserInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierUserInfoPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierUserInfoPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierUserInfoPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
