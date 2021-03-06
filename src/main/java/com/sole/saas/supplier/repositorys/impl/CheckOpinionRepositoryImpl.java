package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.CheckOpinionMapper;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.repositorys.ICheckOpinionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjd
 * @description 供应商审批记录信息数据仓储层实现.
 * @date 2022-05-17
 */
@Repository
public class CheckOpinionRepositoryImpl extends ServiceImpl<CheckOpinionMapper, CheckOpinionPo> implements ICheckOpinionRepository {

    private final CheckOpinionMapper checkOpinionMapper;

    public CheckOpinionRepositoryImpl(CheckOpinionMapper checkOpinionMapper) {
        this.checkOpinionMapper = checkOpinionMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(CheckOpinionPo entity) {
        return super.save(entity);
    }

    @Override
    public List<CheckOpinionPo> getListByParams(CheckOpinionRequest request) {
        final LambdaQueryWrapper<CheckOpinionPo> queryWrapper = this.getQueryWrapper(request);
        queryWrapper.orderByAsc(CheckOpinionPo::getCreateTime);
        return checkOpinionMapper.selectList(queryWrapper);
    }

    @Override
    public Page<CheckOpinionPo> getPageByParams(Page<CheckOpinionPo> page, CheckOpinionRequest request) {
        final LambdaQueryWrapper<CheckOpinionPo> queryWrapper = this.getQueryWrapper(request);
        return checkOpinionMapper.selectPage(page, queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<CheckOpinionPo> getQueryWrapper(CheckOpinionRequest request) {
        LambdaQueryWrapper<CheckOpinionPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(CheckOpinionPo::getId, request.getId());
        }
        if (null != request.getType()) {
            queryWrapper.in(CheckOpinionPo::getType, request.getType());
        }
        if (null != request.getBusinessId()) {
            queryWrapper.in(CheckOpinionPo::getBusinessId, request.getBusinessId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(CheckOpinionPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
