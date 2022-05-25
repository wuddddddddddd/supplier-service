package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierIndustryLogMapper;
import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;
import com.sole.saas.supplier.repositorys.ISupplierIndustryLogRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author wjd
 * @description 供应商主营行业关联关系记录信息数据仓储层实现.
 * @date 2022-05-19
 */
@Repository
public class SupplierIndustryLogRepositoryImpl extends ServiceImpl<SupplierIndustryLogMapper, SupplierIndustryLogPo> implements ISupplierIndustryLogRepository {

    private final SupplierIndustryLogMapper supplierIndustryLogMapper;

    public SupplierIndustryLogRepositoryImpl(SupplierIndustryLogMapper supplierIndustryLogMapper) {
        this.supplierIndustryLogMapper = supplierIndustryLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean saveBatch(Collection<SupplierIndustryLogPo> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierIndustryLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierIndustryLogPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<SupplierIndustryLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierIndustryLogMapper.update(null, updateWrapper);
    }

    @Override
    public SupplierIndustryLogPo getByParams(SupplierIndustryRequest request) {
        final LambdaQueryWrapper<SupplierIndustryLogPo> queryWrapper = this.getQueryWrapper(request);
        return supplierIndustryLogMapper.selectOne(queryWrapper);
    }

    public List<SupplierIndustryLogPo> getListByParams(SupplierIndustryRequest request) {
        final LambdaQueryWrapper<SupplierIndustryLogPo> queryWrapper = this.getQueryWrapper(request);
        return supplierIndustryLogMapper.selectList(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierIndustryLogPo> getQueryWrapper(SupplierIndustryRequest request) {
        LambdaQueryWrapper<SupplierIndustryLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierIndustryLogPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierIndustryLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getIndustryId()) {
            queryWrapper.eq(SupplierIndustryLogPo::getIndustryId, request.getIndustryId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierIndustryLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
