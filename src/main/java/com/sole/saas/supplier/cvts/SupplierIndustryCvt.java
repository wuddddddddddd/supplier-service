package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.response.SupplierIndustryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierIndustryCvt {
    SupplierIndustryCvt INSTANCE = Mappers.getMapper(SupplierIndustryCvt.class);


    List<SupplierIndustryResponse> poToResponseBatch(List<SupplierIndustryPo> poList);

    List<SupplierIndustryResponse> logPoToResponseBatch(List<SupplierIndustryLogPo> logPoList);

    List<SupplierIndustryPo> logPoToPoBatch(List<SupplierIndustryLogPo> logPoList);
}
