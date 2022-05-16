package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.supplier.mappers.RegisterInfoMapper;
import com.sole.saas.supplier.models.po.RegisterInfoPo;
import com.sole.saas.supplier.repositorys.IRegisterInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class RegisterInfoRepositoryImpl extends ServiceImpl<RegisterInfoMapper, RegisterInfoPo> implements IRegisterInfoRepository {

    private final RegisterInfoMapper registerInfoMapper;

    public RegisterInfoRepositoryImpl(RegisterInfoMapper registerInfoMapper) {
        this.registerInfoMapper = registerInfoMapper;
    }
}
