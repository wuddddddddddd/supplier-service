package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.ComplaintInfoPo;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.models.response.ComplaintInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplaintInfoCvt {
    ComplaintInfoCvt INSTANCE = Mappers.getMapper(ComplaintInfoCvt.class);

    ComplaintInfoPo requestToPo(ComplaintInfoRequest request);

    List<ComplaintInfoResponse> poToResponseBatch(List<ComplaintInfoPo> poList);


}
