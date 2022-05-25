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
 * @description 行业资质信息实体转换.
 * @date 2022-05-21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndustryQualificationCvt {
    IndustryQualificationCvt INSTANCE = Mappers.getMapper(IndustryQualificationCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    IndustryQualificationPo requestToPo(IndustryQualificationRequest request);

    /**
     * @description 批量将po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<IndustryQualificationResponse> poToResponseBatch(List<IndustryQualificationPo> poList);
}
