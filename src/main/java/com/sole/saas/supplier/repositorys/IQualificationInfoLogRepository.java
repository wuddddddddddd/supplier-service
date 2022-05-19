package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.QualificationInfoLogPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
public interface IQualificationInfoLogRepository extends IService<QualificationInfoLogPo> {


    int updateByOneParams(SFunction<QualificationInfoLogPo, ?> updateColumn, Object updateValue,
                          SFunction<QualificationInfoLogPo, ?> conditionColumn, Object conditionValue);

    QualificationInfoLogPo getByParams(QualificationInfoRequest request);
}
