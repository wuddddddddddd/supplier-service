package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.RegisterInfoLogPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface IRegisterInfoLogRepository extends IService<RegisterInfoLogPo> {

    int updateByOneParams(SFunction<RegisterInfoLogPo, ?> updateColumn, Object updateValue,
                          SFunction<RegisterInfoLogPo, ?> conditionColumn, Object conditionValue);

    RegisterInfoLogPo getByParams(RegisterInfoRequest request);
}
