package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.models.response.SupplierBasicInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierBasicInfoCvt {
    SupplierBasicInfoCvt INSTANCE = Mappers.getMapper(SupplierBasicInfoCvt.class);

    SupplierBasicInfoPo requestToPo(SupplierBasicInfoRequest request);

    SupplierBasicInfoResponse poToResponse(SupplierBasicInfoPo po);


}
