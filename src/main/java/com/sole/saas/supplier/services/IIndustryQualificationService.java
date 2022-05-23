package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.models.response.IndustryQualificationResponse;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
public interface IIndustryQualificationService {

    void addIndustryQualification(IndustryQualificationRequest request);

    void updateIndustryQualification(IndustryQualificationRequest request);

    void delIndustryQualification(Long id);

    void checkApproval(Long id);

    void checkReject(Long id, String reason, Long currentUserId)

    IPage<IndustryQualificationResponse> getPageInfo(IndustryQualificationRequest request);
}
