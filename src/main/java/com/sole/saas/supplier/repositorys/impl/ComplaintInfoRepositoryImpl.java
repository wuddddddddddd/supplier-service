package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.ComplaintInfoMapper;
import com.sole.saas.supplier.models.po.ComplaintInfoPo;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.repositorys.IComplaintInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Repository
public class ComplaintInfoRepositoryImpl extends ServiceImpl<ComplaintInfoMapper, ComplaintInfoPo> implements IComplaintInfoRepository {

    private final ComplaintInfoMapper complaintInfoMapper;

    public ComplaintInfoRepositoryImpl(ComplaintInfoMapper complaintInfoMapper) {
        this.complaintInfoMapper = complaintInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(ComplaintInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(ComplaintInfoPo entity) {
        return super.updateById(entity);
    }


    @Override
    public int updateByOneParams(SFunction<ComplaintInfoPo, ?> updateColumn, Object updateValue,
                                 SFunction<ComplaintInfoPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<ComplaintInfoPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return complaintInfoMapper.update(null, updateWrapper);
    }


    @Override
    public Page<ComplaintInfoPo> getPageByParams(Page<ComplaintInfoPo> page, ComplaintInfoRequest request) {
        final LambdaQueryWrapper<ComplaintInfoPo> queryWrapper = this.getQueryWrapper(request);
        return complaintInfoMapper.selectPage(page, queryWrapper);
    }


    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<ComplaintInfoPo> getQueryWrapper(ComplaintInfoRequest request) {
        LambdaQueryWrapper<ComplaintInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(ComplaintInfoPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(ComplaintInfoPo::getSupplierId, request.getSupplierId());
        }
        if (StrUtil.isNotBlank(request.getTitle())) {
            queryWrapper.eq(ComplaintInfoPo::getTitle, request.getTitle());
        }
        return queryWrapper;
    }
}
