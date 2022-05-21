package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.IndustryQualificationPo;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.models.response.IndustryQualificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndustryQualificationCvt {
    IndustryQualificationCvt INSTANCE = Mappers.getMapper(IndustryQualificationCvt.class);


    IndustryQualificationPo requestToPo(IndustryQualificationRequest request);

    IndustryQualificationResponse poToResponse(IndustryQualificationPo po);

    List<IndustryQualificationResponse> poToResponseBatch(List<IndustryQualificationPo> poList);
}
