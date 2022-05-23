package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.models.response.ComplaintInfoResponse;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
public interface IComplaintInfoService {


    void addOfflineAchievement(ComplaintInfoRequest request);

    void updateDealDone(Long id, String remark);

    IPage<ComplaintInfoResponse> getPageInfo(ComplaintInfoRequest request);
}
