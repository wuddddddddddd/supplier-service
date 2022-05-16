package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface ISupplierBuyerUserRepository extends IService<SupplierBuyerUserPo> {

    @Override
    @BaseData(fill = OperatorType.INSERT)
    default boolean save(SupplierBuyerUserPo entity) {
        return IService.super.save(entity);
    }
}
