package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBuyerUserLogMapper;
import com.sole.saas.supplier.models.po.SupplierBuyerUserLogPo;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
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
        final LambdaUpdateWrapper<SupplierBuyerUserLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierBuyerUserLogMapper.update(null, updateWrapper);
    }

    @Override
    public SupplierBuyerUserLogPo getByParams(SupplierBuyerUserRequest request) {
        final LambdaQueryWrapper<SupplierBuyerUserLogPo> queryWrapper = this.getQueryWrapper(request);
        return supplierBuyerUserLogMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierBuyerUserLogPo> getQueryWrapper(SupplierBuyerUserRequest request) {
        LambdaQueryWrapper<SupplierBuyerUserLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierBuyerUserLogPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierBuyerUserLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierBuyerUserLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }


}
