package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.OutlinePo;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.OutlineResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description 供应商违规信息实体转换.
 * @date 2022-05-26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OutlineCvt {
    OutlineCvt INSTANCE = Mappers.getMapper(OutlineCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    OutlinePo requestToPo(OutlineRequest request);

    /**
     * @description 批量将po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<OutlineResponse> poToResponseBatch(List<OutlinePo> poList);
}
