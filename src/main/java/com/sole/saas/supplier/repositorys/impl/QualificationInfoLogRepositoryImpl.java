package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.QualificationInfoLogMapper;
import com.sole.saas.supplier.models.po.QualificationInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.repositorys.IQualificationInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 供应商资质信息数据仓储层实现.
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

    @Override
    public int updateByOneParams(SFunction<QualificationInfoLogPo, ?> updateColumn, Object updateValue,
                                 SFunction<QualificationInfoLogPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<QualificationInfoLogPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return qualificationInfoLogMapper.update(null, updateWrapper);
    }

    @Override
    public QualificationInfoLogPo getByParams(QualificationInfoRequest request) {
        final LambdaQueryWrapper<QualificationInfoLogPo> queryWrapper = this.getQueryWrapper(request);
        return qualificationInfoLogMapper.selectOne(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<QualificationInfoLogPo> getQueryWrapper(QualificationInfoRequest request) {
        LambdaQueryWrapper<QualificationInfoLogPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(QualificationInfoLogPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(QualificationInfoLogPo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(QualificationInfoLogPo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
