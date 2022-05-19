package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBasicInfoLogMapper;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
@Repository
public class SupplierBasicInfoLogRepositoryImpl extends ServiceImpl<SupplierBasicInfoLogMapper, SupplierBasicInfoLogPo> implements ISupplierBasicInfoLogRepository {

    private final SupplierBasicInfoLogMapper supplierBasicInfoLogMapper;

    public SupplierBasicInfoLogRepositoryImpl(SupplierBasicInfoLogMapper supplierBasicInfoLogMapper) {
        this.supplierBasicInfoLogMapper = supplierBasicInfoLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBasicInfoLogPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierBasicInfoLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<SupplierBasicInfoLogPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<SupplierBasicInfoLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return supplierBasicInfoLogMapper.update(null, updateWrapper);
    }

    @Override
    public SupplierBasicInfoLogPo getByParams(SupplierBasicInfoRequest request) {
        final LambdaQueryWrapper<SupplierBasicInfoLogPo> queryWrapper = this.getQueryWrapper(request);
        return supplierBasicInfoLogMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<SupplierPageResponse> getCustomerLogPage(Page<SupplierBasicInfoPo> page, SupplierPageRequest request) {
        return supplierBasicInfoLogMapper.getCustomerLogPage(page, request);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<SupplierBasicInfoLogPo> getQueryWrapper(SupplierBasicInfoRequest request) {
        LambdaQueryWrapper<SupplierBasicInfoLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(SupplierBasicInfoLogPo::getId, request.getId());
        }
        if (StrUtil.isNotBlank(request.getName())) {
            queryWrapper.eq(SupplierBasicInfoLogPo::getName, request.getName());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(SupplierBasicInfoLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(SupplierBasicInfoLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }


}
