package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierBasicInfoLogRepository extends IService<SupplierBasicInfoLogPo> {

    int updateByOneParams(SFunction<SupplierBasicInfoLogPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierBasicInfoLogPo, ?> conditionColumn, Object conditionValue);

}
