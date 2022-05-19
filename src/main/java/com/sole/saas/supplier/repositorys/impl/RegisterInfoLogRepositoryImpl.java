package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.RegisterInfoLogMapper;
import com.sole.saas.supplier.models.po.RegisterInfoLogPo;
import com.sole.saas.supplier.repositorys.IRegisterInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
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
}
