package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.OfflineAchievementPo;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.models.response.OfflineAchievementResponse;
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
public interface OfflineAchievementCvt {
    OfflineAchievementCvt INSTANCE = Mappers.getMapper(OfflineAchievementCvt.class);


    OfflineAchievementPo requestToPo(OfflineAchievementRequest request);

    List<OfflineAchievementResponse> poToResponseBatch(List<OfflineAchievementPo> poList);
}
