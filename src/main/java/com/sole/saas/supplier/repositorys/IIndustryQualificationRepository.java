package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.IndustryQualificationPo;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
public interface IIndustryQualificationRepository extends IService<IndustryQualificationPo> {


    int updateByOneParams(SFunction<IndustryQualificationPo, ?> updateColumn, Object updateValue,
                          SFunction<IndustryQualificationPo, ?> conditionColumn, Object conditionValue);

    Page<IndustryQualificationPo> getPageByParams(Page<IndustryQualificationPo> page, IndustryQualificationRequest request);
}
