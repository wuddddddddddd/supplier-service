package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.OfflineAchievementMapper;
import com.sole.saas.supplier.models.po.OfflineAchievementPo;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.repositorys.IOfflineAchievementRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description 线下业绩信息数据仓储层实现.
 * @date 2022-05-21
 */
@Repository
public class OfflineAchievementRepositoryImpl extends ServiceImpl<OfflineAchievementMapper, OfflineAchievementPo> implements IOfflineAchievementRepository {

    private final OfflineAchievementMapper offlineAchievementMapper;

    public OfflineAchievementRepositoryImpl(OfflineAchievementMapper offlineAchievementMapper) {
        this.offlineAchievementMapper = offlineAchievementMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(OfflineAchievementPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(OfflineAchievementPo entity) {
        return super.updateById(entity);
    }


    @Override
    public int updateByOneParams(SFunction<OfflineAchievementPo, ?> updateColumn, Object updateValue,
                                 SFunction<OfflineAchievementPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<OfflineAchievementPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return offlineAchievementMapper.update(null, updateWrapper);
    }

    @Override
    public Page<OfflineAchievementPo> getPageByParams(Page<OfflineAchievementPo> page, OfflineAchievementRequest request) {
        final LambdaQueryWrapper<OfflineAchievementPo> queryWrapper = this.getQueryWrapper(request);
        return offlineAchievementMapper.selectPage(page, queryWrapper);
    }


    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<OfflineAchievementPo> getQueryWrapper(OfflineAchievementRequest request) {
        LambdaQueryWrapper<OfflineAchievementPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(OfflineAchievementPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(OfflineAchievementPo::getSupplierId, request.getSupplierId());
        }
        return queryWrapper;
    }
}
