package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.models.response.OfflineAchievementResponse;

/**
 * @author wjd
 * @description 线下业绩信息服务层接口.
 * @date 2022-05-21
 */
public interface IOfflineAchievementService {

    /**
      * @description 新增线下业绩.
      * @author wjd
      * @date 2022/5/25
      * @param request 待新增的实体
      */
    void addOfflineAchievement(OfflineAchievementRequest request);

    /**
     * @description 修改线下业绩.
     * @author wjd
     * @date 2022/5/25
     * @param request 待修改的实体
     */
    void updateOfflineAchievement(OfflineAchievementRequest request);

    /**
      * @description 根据主键ID删除.
      * @author wjd
      * @date 2022/5/25
      * @param id 待删除的主键ID
      */
    void delOfflineAchievement(Long id);

    /**
      * @description 线下业绩信息审批通过.
      * @author wjd
      * @date 2022/5/25
      * @param id 待审批通过的线下业绩主键ID
      * @param currentUserId 当前审批人
      */
    void checkApproval(Long id, Long currentUserId);

    /**
     * @description 线下业绩信息审批驳回.
     * @author wjd
     * @date 2022/5/25
     * @param id 待审批驳回的线下业绩主键ID
     * @param currentUserId 当前审批人
     */
    void checkReject(Long id, String reason, Long currentUserId);

    /**
      * @description 根据自定义查询线下业绩结果集带分页
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 线下业绩结果集带分页
      */
    IPage<OfflineAchievementResponse> getPageInfo(OfflineAchievementRequest request);
}
