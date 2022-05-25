package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;

import java.util.List;

/**
 * @author wjd
 * @description 供应商审批记录信息数据仓储层接口.
 * @date 2022-05-17
 */
public interface ICheckOpinionRepository extends IService<CheckOpinionPo> {

    /**
      * @description 根据自定义条件查询结果集.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商审批记录信息集
      */
    List<CheckOpinionPo> getListByParams(CheckOpinionRequest request);
}
