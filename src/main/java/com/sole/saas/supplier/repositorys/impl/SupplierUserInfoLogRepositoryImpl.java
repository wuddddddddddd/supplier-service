package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierUserInfoLogMapper;
import com.sole.saas.supplier.models.po.SupplierUserInfoLogPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;
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
        final LambdaUpdateWrapper<SupplierUserInfoLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierUserInfoLogMapper.update(null, updateWrapper);
    }


    @Override
    public SupplierUserInfoLogPo getByParams(SupplierUserInfoRequest request) {
        final LambdaQueryWrapper<SupplierUserInfoLogPo> queryWrapper = this.getQueryWrapper(request);
        return supplierUserInfoLogMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierUserInfoLogPo> getQueryWrapper(SupplierUserInfoRequest request) {
        LambdaQueryWrapper<SupplierUserInfoLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierUserInfoLogPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierUserInfoLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierUserInfoLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
