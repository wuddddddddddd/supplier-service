package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.ComplaintInfoPo;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
public interface IComplaintInfoRepository extends IService<ComplaintInfoPo> {

    int updateByOneParams(SFunction<ComplaintInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<ComplaintInfoPo, ?> conditionColumn, Object conditionValue);

    int updateByParams(ComplaintInfoPo complaintInfoPo, ComplaintInfoRequest request);

    Page<ComplaintInfoPo> getPageByParams(Page<ComplaintInfoPo> page, ComplaintInfoRequest request);
}
