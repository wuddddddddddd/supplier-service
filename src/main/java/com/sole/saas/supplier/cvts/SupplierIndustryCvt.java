package com.sole.saas.supplier.cvts;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierIndustryCvt {
    SupplierIndustryCvt INSTANCE = Mappers.getMapper(SupplierIndustryCvt.class);
}
