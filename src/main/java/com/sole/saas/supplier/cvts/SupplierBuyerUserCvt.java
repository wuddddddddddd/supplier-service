package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierBuyerUserCvt {
    SupplierBuyerUserCvt INSTANCE = Mappers.getMapper(SupplierBuyerUserCvt.class);

    SupplierBuyerUserPo requestToPo(SupplierBuyerUserRequest request);
}
