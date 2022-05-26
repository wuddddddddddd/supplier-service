package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.BusinessHistoryMapper;
import com.sole.saas.supplier.models.po.BusinessHistoryPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.BusinessHistoryRequest;
import com.sole.saas.supplier.repositorys.IBusinessHistoryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 供应商业务状态变更记录数据仓储层实现.
 * @date 2022-05-18
 */
@Repository
public class BusinessHistoryRepositoryImpl extends ServiceImpl<BusinessHistoryMapper, BusinessHistoryPo> implements IBusinessHistoryRepository {

    private final BusinessHistoryMapper businessHistoryMapper;

    public BusinessHistoryRepositoryImpl(BusinessHistoryMapper businessHistoryMapper) {
        this.businessHistoryMapper = businessHistoryMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(BusinessHistoryPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<BusinessHistoryPo, ?> updateColumn, Object updateValue,
                                 SFunction<BusinessHistoryPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<BusinessHistoryPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return businessHistoryMapper.update(null, updateWrapper);
    }

    @Override
    public BusinessHistoryPo getByParams(BusinessHistoryRequest request) {
        final LambdaQueryWrapper<BusinessHistoryPo> queryWrapper = this.getQueryWrapper(request);
        return businessHistoryMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<BusinessHistoryPo> getQueryWrapper(BusinessHistoryRequest request) {
        LambdaQueryWrapper<BusinessHistoryPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(BusinessHistoryPo::getId, request.getId());
        }
        if (null != request.getBusinessId()) {
            queryWrapper.eq(BusinessHistoryPo::getBusinessId, request.getBusinessId());
        }
        if (null != request.getType()) {
            queryWrapper.eq(BusinessHistoryPo::getType, request.getType());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(BusinessHistoryPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
