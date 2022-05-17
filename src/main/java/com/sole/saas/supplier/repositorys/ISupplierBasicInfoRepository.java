package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierBasicInfoRepository extends IService<SupplierBasicInfoPo> {

    int updateByOneParams(SFunction<SupplierBasicInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierBasicInfoPo, ?> conditionColumn, Object conditionValue);
}
