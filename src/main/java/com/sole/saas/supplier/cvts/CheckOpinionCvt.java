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
 * @description 供应商审批记录信息实体转换.
 * @date 2022-05-17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckOpinionCvt {
    CheckOpinionCvt INSTANCE = Mappers.getMapper(CheckOpinionCvt.class);

    /**
      * @description request实体转换成po实体.
      * @author wjd
      * @date 2022/5/24
      * @param request 待转换的request实体
      * @return 转换后的po实体
      */
    CheckOpinionPo requestToPo(CheckOpinionRequest request);

    /**
     * @description 批量将po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<CheckOpinionResponse> poToResponseBatch(List<CheckOpinionPo> poList);
}
