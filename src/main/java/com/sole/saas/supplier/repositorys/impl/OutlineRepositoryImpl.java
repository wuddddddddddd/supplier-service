package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.OutlineMapper;
import com.sole.saas.supplier.models.po.OutlinePo;
import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;
import com.sole.saas.supplier.models.response.OutLinePageResponse;
import com.sole.saas.supplier.models.response.OutlineResponse;
import com.sole.saas.supplier.repositorys.IOutlineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjd
 * @description 供应商违规信息仓储层接口实现.
 * @date 2022-05-26
 */
@Repository
public class OutlineRepositoryImpl extends ServiceImpl<OutlineMapper, OutlinePo> implements IOutlineRepository {

    private final OutlineMapper outlineMapper;

    public OutlineRepositoryImpl(OutlineMapper outlineMapper) {
        this.outlineMapper = outlineMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(OutlinePo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<OutlinePo, ?> updateColumn, Object updateValue,
                                 SFunction<OutlinePo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<OutlinePo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return outlineMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<OutLinePageResponse> getCustomerPage(Page<OutlinePo> page, OutlinePageRequest request) {
        return outlineMapper.getCustomerPage(page, request);
    }

    @Override
    public List<OutlinePo> getListByParams(OutlineRequest request) {
        final LambdaQueryWrapper<OutlinePo> queryWrapper = this.getQueryWrapper(request);
        return outlineMapper.selectList(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<OutlinePo> getQueryWrapper(OutlineRequest request) {
        LambdaQueryWrapper<OutlinePo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(OutlinePo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(OutlinePo::getSupplierId, request.getSupplierId());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(OutlinePo::getStatus ,request.getStatus());
        }
        return queryWrapper;
    }
}
