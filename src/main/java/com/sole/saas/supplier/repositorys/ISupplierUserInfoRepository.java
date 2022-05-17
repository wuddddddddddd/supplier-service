package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface ISupplierUserInfoRepository extends IService<SupplierUserInfoPo> {

    SupplierUserInfoPo getByParams(SupplierUserInfoRequest request);
}
