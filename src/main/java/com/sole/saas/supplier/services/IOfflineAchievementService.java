package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.models.response.OfflineAchievementResponse;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
public interface IOfflineAchievementService {

    void addOfflineAchievement(OfflineAchievementRequest request);

    void updateOfflineAchievement(OfflineAchievementRequest request);

    void delOfflineAchievement(Long id);

    void checkApproval(Long id);

    void checkReject(Long id, String reason, Long currentUserId);

    IPage<OfflineAchievementResponse> getPageInfo(OfflineAchievementRequest request);
}
