package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.RegisterInfoPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface IRegisterInfoRepository extends IService<RegisterInfoPo> {

    RegisterInfoPo getByParams(RegisterInfoRequest request);
}
