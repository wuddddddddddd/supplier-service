package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.RegisterInfoLogMapper;
import com.sole.saas.supplier.models.po.RegisterInfoLogPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;
import com.sole.saas.supplier.repositorys.IRegisterInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 供应商基础信息数据仓储层实现.
 * @date 2022-05-19
 */
@Repository
public class RegisterInfoLogRepositoryImpl extends ServiceImpl<RegisterInfoLogMapper, RegisterInfoLogPo> implements IRegisterInfoLogRepository {

    private final RegisterInfoLogMapper registerInfoLogMapper;

    public RegisterInfoLogRepositoryImpl(RegisterInfoLogMapper registerInfoLogMapper) {
        this.registerInfoLogMapper = registerInfoLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(RegisterInfoLogPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<RegisterInfoLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<RegisterInfoLogPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<RegisterInfoLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return registerInfoLogMapper.update(null, updateWrapper);
    }

    @Override
    public RegisterInfoLogPo getByParams(RegisterInfoRequest request) {
        final LambdaQueryWrapper<RegisterInfoLogPo> queryWrapper = this.getQueryWrapper(request);
        return registerInfoLogMapper.selectOne(queryWrapper);
    }


    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<RegisterInfoLogPo> getQueryWrapper(RegisterInfoRequest request) {
        LambdaQueryWrapper<RegisterInfoLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(RegisterInfoLogPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(RegisterInfoLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(RegisterInfoLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
