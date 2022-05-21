package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface IQualificationInfoRepository extends IService<QualificationInfoPo> {

    int updateByOneParams(SFunction<QualificationInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<QualificationInfoPo, ?> conditionColumn, Object conditionValue);

    QualificationInfoPo getByParams(QualificationInfoRequest request);
}
