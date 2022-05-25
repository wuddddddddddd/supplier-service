package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.RegisterInfoMapper;
import com.sole.saas.supplier.models.po.RegisterInfoPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;
import com.sole.saas.supplier.repositorys.IRegisterInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 供应商公司注册信息数据仓储层实现.
 * @date 2022-05-16
 */
@Repository
public class RegisterInfoRepositoryImpl extends ServiceImpl<RegisterInfoMapper, RegisterInfoPo> implements IRegisterInfoRepository {

    private final RegisterInfoMapper registerInfoMapper;

    public RegisterInfoRepositoryImpl(RegisterInfoMapper registerInfoMapper) {
        this.registerInfoMapper = registerInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(RegisterInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(RegisterInfoPo entity) {
        return super.updateById(entity);
    }


    @Override
    public RegisterInfoPo getByParams(RegisterInfoRequest request) {
        final LambdaQueryWrapper<RegisterInfoPo> queryWrapper = this.getQueryWrapper(request);
        return registerInfoMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<RegisterInfoPo> getQueryWrapper(RegisterInfoRequest request) {
        LambdaQueryWrapper<RegisterInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(RegisterInfoPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(RegisterInfoPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(RegisterInfoPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
