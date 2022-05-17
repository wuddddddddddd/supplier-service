package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckOpinionCvt {
    CheckOpinionCvt INSTANCE = Mappers.getMapper(CheckOpinionCvt.class);

    CheckOpinionPo requestToPo(CheckOpinionRequest request);

    List<CheckOpinionResponse> poToResponseBatch(List<CheckOpinionPo> list);
}
