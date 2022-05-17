package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
public interface ICheckOpinionRepository extends IService<CheckOpinionPo> {

    List<CheckOpinionPo> getListByParams(CheckOpinionRequest request);
}
