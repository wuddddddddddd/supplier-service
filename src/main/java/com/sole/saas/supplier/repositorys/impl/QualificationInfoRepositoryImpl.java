package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.QualificationInfoMapper;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;
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

    @Override
    public QualificationInfoPo getByParams(QualificationInfoRequest request) {
        final LambdaQueryWrapper<QualificationInfoPo> queryWrapper = this.getQueryWrapper(request);
        return qualificationInfoMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<QualificationInfoPo> getQueryWrapper(QualificationInfoRequest request) {
        LambdaQueryWrapper<QualificationInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(QualificationInfoPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(QualificationInfoPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(QualificationInfoPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
