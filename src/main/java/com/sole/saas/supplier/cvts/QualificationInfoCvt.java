package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.QualificationInfoLogPo;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;
import com.sole.saas.supplier.models.response.QualificationInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QualificationInfoCvt {
    QualificationInfoCvt INSTANCE = Mappers.getMapper(QualificationInfoCvt.class);


    QualificationInfoPo  requestToPo(QualificationInfoRequest request);

    QualificationInfoResponse poToResponse(QualificationInfoPo po);

    QualificationInfoLogPo poToLogPo(QualificationInfoPo po);
}
