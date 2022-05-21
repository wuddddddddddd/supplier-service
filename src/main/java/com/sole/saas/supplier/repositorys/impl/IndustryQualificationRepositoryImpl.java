package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.IndustryQualificationMapper;
import com.sole.saas.supplier.models.po.IndustryQualificationPo;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.repositorys.IIndustryQualificationRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
@Repository
public class IndustryQualificationRepositoryImpl extends ServiceImpl<IndustryQualificationMapper, IndustryQualificationPo> implements IIndustryQualificationRepository {

    private final IndustryQualificationMapper industryQualificationMapper;

    public IndustryQualificationRepositoryImpl(IndustryQualificationMapper industryQualificationMapper) {
        this.industryQualificationMapper = industryQualificationMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(IndustryQualificationPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(IndustryQualificationPo entity) {
        return super.updateById(entity);
    }

    @Override
    public int updateByOneParams(SFunction<IndustryQualificationPo, ?> updateColumn, Object updateValue,
                                 SFunction<IndustryQualificationPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<IndustryQualificationPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return industryQualificationMapper.update(null, updateWrapper);
    }


    @Override
    public Page<IndustryQualificationPo> getPageByParams(Page<IndustryQualificationPo> page, IndustryQualificationRequest request) {
        final LambdaQueryWrapper<IndustryQualificationPo> queryWrapper = this.getQueryWrapper(request);
        return industryQualificationMapper.selectPage(page, queryWrapper);
    }


    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<IndustryQualificationPo> getQueryWrapper(IndustryQualificationRequest request) {
        LambdaQueryWrapper<IndustryQualificationPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(IndustryQualificationPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(IndustryQualificationPo::getSupplierId, request.getSupplierId());
        }
        return queryWrapper;
    }
}
