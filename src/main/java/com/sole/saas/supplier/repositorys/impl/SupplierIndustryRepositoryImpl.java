package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierIndustryMapper;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;
import com.sole.saas.supplier.repositorys.ISupplierIndustryRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author wjd
 * @description 供应商主营行业关联关系数据仓储层实现.
 * @date 2022-05-19
 */
@Repository
public class SupplierIndustryRepositoryImpl extends ServiceImpl<SupplierIndustryMapper, SupplierIndustryPo> implements ISupplierIndustryRepository {

    private final SupplierIndustryMapper supplierIndustryMapper;

    public SupplierIndustryRepositoryImpl(SupplierIndustryMapper supplierIndustryMapper) {
        this.supplierIndustryMapper = supplierIndustryMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean saveBatch(Collection<SupplierIndustryPo> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return false;
        }
        return super.saveBatch(entityList);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierIndustryPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierIndustryPo, ?> conditionColumn, Object conditionValue) {

        final LambdaUpdateWrapper<SupplierIndustryPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierIndustryMapper.update(null, updateWrapper);
    }

    @Override
    public SupplierIndustryPo getByParams(SupplierIndustryRequest request) {
        final LambdaQueryWrapper<SupplierIndustryPo> queryWrapper = this.getQueryWrapper(request);
        return supplierIndustryMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SupplierIndustryPo> getListByParams(SupplierIndustryRequest request) {
        final LambdaQueryWrapper<SupplierIndustryPo> queryWrapper = this.getQueryWrapper(request);
        if (CollectionUtil.isNotEmpty(request.getSupplierIdList())) {
            queryWrapper.in(SupplierIndustryPo::getSupplierId, request.getSupplierIdList());
        }
        return supplierIndustryMapper.selectList(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierIndustryPo> getQueryWrapper(SupplierIndustryRequest request) {
        LambdaQueryWrapper<SupplierIndustryPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierIndustryPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierIndustryPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierIndustryPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
