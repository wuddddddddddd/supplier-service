package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.BusinessHistoryPo;
import com.sole.saas.supplier.models.request.BusinessHistoryRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-18
 */
public interface IBusinessHistoryRepository extends IService<BusinessHistoryPo> {

    int updateByOneParams(SFunction<BusinessHistoryPo, ?> updateColumn, Object updateValue,
                          SFunction<BusinessHistoryPo, ?> conditionColumn, Object conditionValue);

    BusinessHistoryPo getByParams(BusinessHistoryRequest request);

}
