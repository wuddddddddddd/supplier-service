package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
public interface ISupplierIndustryLogRepository extends IService<SupplierIndustryLogPo> {

    int updateByOneParams(SFunction<SupplierIndustryLogPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierIndustryLogPo, ?> conditionColumn, Object conditionValue);

    SupplierIndustryLogPo getByParams(SupplierIndustryRequest request);

    List<SupplierIndustryLogPo> getListByParams(SupplierIndustryRequest request);
}
