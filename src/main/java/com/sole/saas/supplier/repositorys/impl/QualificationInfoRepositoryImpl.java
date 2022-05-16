package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.QualificationInfoMapper;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.repositorys.IQualificationInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class QualificationInfoRepositoryImpl extends ServiceImpl<QualificationInfoMapper, QualificationInfoPo> implements IQualificationInfoRepository {

    private final QualificationInfoMapper qualificationInfoMapper;

    public QualificationInfoRepositoryImpl(QualificationInfoMapper qualificationInfoMapper) {
        this.qualificationInfoMapper = qualificationInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(QualificationInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(QualificationInfoPo entity) {
        return super.updateById(entity);
    }
}
