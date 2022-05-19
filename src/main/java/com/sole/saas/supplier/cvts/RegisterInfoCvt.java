package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.RegisterInfoLogPo;
import com.sole.saas.supplier.models.po.RegisterInfoPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;
import com.sole.saas.supplier.models.response.RegisterInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterInfoCvt {
    RegisterInfoCvt INSTANCE = Mappers.getMapper(RegisterInfoCvt.class);


    RegisterInfoPo requestToPo(RegisterInfoRequest request);

    RegisterInfoResponse poToResponse(RegisterInfoPo po);

    RegisterInfoLogPo poToLogPo(RegisterInfoPo po);

    RegisterInfoLogPo requestToLogPo(RegisterInfoRequest request);


}
