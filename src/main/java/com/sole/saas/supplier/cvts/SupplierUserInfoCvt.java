package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierUserInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;
import com.sole.saas.supplier.models.response.SupplierUserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierUserInfoCvt {
    SupplierUserInfoCvt INSTANCE = Mappers.getMapper(SupplierUserInfoCvt.class);

    SupplierUserInfoPo requestToPo(SupplierUserInfoRequest request);

    SupplierUserInfoResponse poToResponse(SupplierUserInfoPo po);

    SupplierUserInfoLogPo poToLogPo(SupplierUserInfoPo po);

    SupplierUserInfoLogPo requestToLogPo(SupplierUserInfoRequest request);

    SupplierUserInfoResponse logPoToResponse(SupplierUserInfoLogPo logPo);

    SupplierUserInfoPo logPoToPo(SupplierUserInfoLogPo logPo);
}
