package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierDictMapper;
import com.sole.saas.supplier.models.po.SupplierDictPo;
import com.sole.saas.supplier.models.request.SupplierDictRequest;
import com.sole.saas.supplier.repositorys.ISupplierDictRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
@Repository
public class SupplierDictRepositoryImpl extends ServiceImpl<SupplierDictMapper, SupplierDictPo> implements ISupplierDictRepository {

    private final SupplierDictMapper supplierDictMapper;

    public SupplierDictRepositoryImpl(SupplierDictMapper supplierDictMapper) {
        this.supplierDictMapper = supplierDictMapper;
    }


    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean saveBatch(Collection<SupplierDictPo> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return false;
        }
        return super.saveBatch(entityList);
    }

    @Override
    public int updateOneByParams(SFunction<SupplierDictPo, ?> updateColumn, Object updateValue,
                                 SupplierDictRequest request) {
        final LambdaUpdateWrapper<SupplierDictPo> updateWrapper = this.getUpdateWrapper(request);
        updateWrapper.set(updateColumn, updateValue);
        return supplierDictMapper.update(null, updateWrapper);
    }

    @Override
    public List<SupplierDictPo> getListByParams(SupplierDictRequest request) {
        final LambdaQueryWrapper<SupplierDictPo> queryWrapper = this.getQueryWrapper(request);
        return supplierDictMapper.selectList(queryWrapper);
    }


    private LambdaQueryWrapper<SupplierDictPo> getQueryWrapper(SupplierDictRequest request) {
        LambdaQueryWrapper<SupplierDictPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierDictPo::getId, request.getId());
        }
        if (StrUtil.isNotBlank(request.getCode())) {
            queryWrapper.eq(SupplierDictPo::getCode, request.getCode());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierDictPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getDictId()) {
            queryWrapper.eq(SupplierDictPo::getDictId, request.getDictId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierDictPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }

    private LambdaUpdateWrapper<SupplierDictPo> getUpdateWrapper(SupplierDictRequest request) {
        LambdaUpdateWrapper<SupplierDictPo> queryWrapper = new LambdaUpdateWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierDictPo::getId, request.getId());
        }
        if (StrUtil.isNotBlank(request.getCode())) {
            queryWrapper.eq(SupplierDictPo::getCode, request.getCode());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierDictPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getDictId()) {
            queryWrapper.eq(SupplierDictPo::getDictId, request.getDictId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierDictPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
