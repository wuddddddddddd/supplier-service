package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.ContractInfoPo;
import com.sole.saas.supplier.models.request.ContractInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
public interface IContractInfoRepository extends IService<ContractInfoPo> {

    int updateByOneParams(SFunction<ContractInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<ContractInfoPo, ?> conditionColumn, Object conditionValue);

    Page<ContractInfoPo> getPageByParams(Page<ContractInfoPo> page, ContractInfoRequest request);
}
