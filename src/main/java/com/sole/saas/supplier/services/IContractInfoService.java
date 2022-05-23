package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.ContractInfoRequest;
import com.sole.saas.supplier.models.response.ContractInfoResponse;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
public interface IContractInfoService {


    void addOfflineAchievement(ContractInfoRequest request);

    void updateBusinessStatus(Long id);

    IPage<ContractInfoResponse> getPageInfo(ContractInfoRequest request);
}
