package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.ContractInfoPo;
import com.sole.saas.supplier.models.request.ContractInfoRequest;
import com.sole.saas.supplier.models.response.ContractInfoResponse;
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
public interface ContractInfoCvt {
    ContractInfoCvt INSTANCE = Mappers.getMapper(ContractInfoCvt.class);

    ContractInfoPo requestToPo(ContractInfoRequest request);

    List<ContractInfoResponse> poToResponseBatch(List<ContractInfoPo> poList);
}
