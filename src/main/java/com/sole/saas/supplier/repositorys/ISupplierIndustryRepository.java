package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
public interface ISupplierIndustryRepository extends IService<SupplierIndustryPo> {

    SupplierIndustryPo getByParams(SupplierIndustryRequest request);

    List<SupplierIndustryPo> getListByParams(SupplierIndustryRequest request);

}
