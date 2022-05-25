package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;

import java.util.List;

/**
 * @author wjd
 * @description 供应商审批记录信息服务层接口.
 * @date 2022-05-17
 */
public interface ICheckOpinionService {

    /**
      * @description 根据自定义条件查询结果集.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商审批记录信息集
      */
    List<CheckOpinionResponse> getOpinionListByParams(CheckOpinionRequest request);

    /**
      * @description 根据自定义条件查询结果集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商审批记录信息集带分页
      */
    IPage<CheckOpinionResponse> getPageInfo(CheckOpinionRequest request);
}
