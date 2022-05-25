package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.models.response.IndustryQualificationResponse;

/**
 * @author wjd
 * @description 行业资质信息服务层接口.
 * @date 2022-05-21
 */
public interface IIndustryQualificationService {

    /**
      * @description 新增行业资质信息.
      * @author wjd
      * @date 2022/5/25
      * @param request 待新增的实体
      */
    void addIndustryQualification(IndustryQualificationRequest request);

    /**
     * @description 修改行业资质信息.
     * @author wjd
     * @date 2022/5/25
     * @param request 待修改的实体
     */
    void updateIndustryQualification(IndustryQualificationRequest request);

    /**
      * @description 根据主键ID进行删除.
      * @author wjd
      * @date 2022/5/25
      * @param id 待删除的行业资质信息主键ID.
      */
    void delIndustryQualification(Long id);

    /**
      * @description 行业资质审批通过.
      * @author wjd
      * @date 2022/5/25
      * @param id 待审批通过的行业资质主键ID
      * @param currentUserId 当前审批人
      */
    void checkApproval(Long id, Long currentUserId);

    /**
     * @description 行业资质审批驳回.
     * @author wjd
     * @date 2022/5/25
     * @param id 待审批驳回的行业资质主键ID
     * @param currentUserId 当前审批人
     */
    void checkReject(Long id, String reason, Long currentUserId);

    /**
      * @description 根据自定义条件查询结果集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 行业资质信息结果集带分页
      */
    IPage<IndustryQualificationResponse> getPageInfo(IndustryQualificationRequest request);
}
