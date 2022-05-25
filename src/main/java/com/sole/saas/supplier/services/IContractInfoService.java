package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.ContractInfoRequest;
import com.sole.saas.supplier.models.response.ContractInfoResponse;

/**
 * @author wjd
 * @description 供应商合同信息服务层接口.
 * @date 2022-05-23
 */
public interface IContractInfoService {

    /**
      * @description 新增供应商合同信息.
      * @author wjd
      * @date 2022/5/25
      * @param request 待新增的实体
      */
    void addOfflineAchievement(ContractInfoRequest request);

    /**
      * @description 根据ID更新合同业务状态.
      * @author wjd
      * @date 2022/5/25
      * @param id 待更新的合同主键ID
      */
    void updateBusinessStatus(Long id);

    /**
      * @description 根据条件查询结果集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商合同信息集带分页
      */
    IPage<ContractInfoResponse> getPageInfo(ContractInfoRequest request);
}
