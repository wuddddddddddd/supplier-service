package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.models.response.ComplaintInfoResponse;

/**
 * @author wjd
 * @description 供应商投诉信息服务层接口.
 * @date 2022-05-23
 */
public interface IComplaintInfoService {

    /**
      * @description 新增投诉信息.
      * @author wjd
      * @date 2022/5/25
      * @param request 待新增的投诉信息实体.
      */
    void addOfflineAchievement(ComplaintInfoRequest request);

    /**
      * @description 修改投诉信息业务状态为已处理.
      * @author wjd
      * @date 2022/5/25
      * @param id 待修改的投诉信息
      * @param remark 备注
      */
    void updateDealDone(Long id, String remark);

    /**
      * @description 根据自定义条件查询投诉信息集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 投诉信息集带分页
      */
    IPage<ComplaintInfoResponse> getPageInfo(ComplaintInfoRequest request);
}
