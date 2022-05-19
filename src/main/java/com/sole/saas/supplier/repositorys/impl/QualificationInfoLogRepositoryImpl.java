package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.QualificationInfoLogMapper;
import com.sole.saas.supplier.models.po.QualificationInfoLogPo;
import com.sole.saas.supplier.repositorys.IQualificationInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class QualificationInfoLogRepositoryImpl extends ServiceImpl<QualificationInfoLogMapper, QualificationInfoLogPo> implements IQualificationInfoLogRepository {

    private final QualificationInfoLogMapper qualificationInfoLogMapper;

    public QualificationInfoLogRepositoryImpl(QualificationInfoLogMapper qualificationInfoLogMapper) {
        this.qualificationInfoLogMapper = qualificationInfoLogMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(QualificationInfoLogPo entity) {
        return super.save(entity);
    }
}
