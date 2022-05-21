package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface ISupplierBuyerUserRepository extends IService<SupplierBuyerUserPo> {

    SupplierBuyerUserPo getByParams(SupplierBuyerUserRequest request);

    List<SupplierBuyerUserPo> getListByParams(SupplierBuyerUserRequest request);
}
