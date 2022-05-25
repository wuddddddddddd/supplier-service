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
 * @description 线下业绩信息实体转换.
 * @date 2022-05-21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfflineAchievementCvt {
    OfflineAchievementCvt INSTANCE = Mappers.getMapper(OfflineAchievementCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    OfflineAchievementPo requestToPo(OfflineAchievementRequest request);

    /**
     * @description 批量将po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<OfflineAchievementResponse> poToResponseBatch(List<OfflineAchievementPo> poList);
}
