package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.OfflineAchievementPo;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
public interface IOfflineAchievementRepository extends IService<OfflineAchievementPo> {

    int updateByOneParams(SFunction<OfflineAchievementPo, ?> updateColumn, Object updateValue,
                          SFunction<OfflineAchievementPo, ?> conditionColumn, Object conditionValue);

    Page<OfflineAchievementPo> getPageByParams(Page<OfflineAchievementPo> page, OfflineAchievementRequest request);
}
