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
 * @description 供应商投诉信息实体转换.
 * @date 2022-05-23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplaintInfoCvt {
    ComplaintInfoCvt INSTANCE = Mappers.getMapper(ComplaintInfoCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    ComplaintInfoPo requestToPo(ComplaintInfoRequest request);

    /**
     * @description 批量将po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<ComplaintInfoResponse> poToResponseBatch(List<ComplaintInfoPo> poList);


}
