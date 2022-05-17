package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.DictInfoMapper;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
@Repository
public class DictInfoRepositoryImpl extends ServiceImpl<DictInfoMapper, DictInfoPo> implements IDictInfoRepository {

    private final DictInfoMapper dictInfoMapper;

    public DictInfoRepositoryImpl(DictInfoMapper dictInfoMapper) {
        this.dictInfoMapper = dictInfoMapper;
    }

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(DictInfoPo entity) {
        return super.save(entity);
    }


    @Override
    public List<DictInfoPo> getListByParams(DictInfoRequest request) {
        final LambdaQueryWrapper<DictInfoPo> queryWrapper = this.getQueryWrapper(request);
        if (CollectionUtil.isNotEmpty(request.getIdList())) {
            queryWrapper.in(DictInfoPo::getId, request.getIdList());
        }
        return dictInfoMapper.selectList(queryWrapper);
    }

    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<DictInfoPo> getQueryWrapper(DictInfoRequest request) {
        LambdaQueryWrapper<DictInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(DictInfoPo::getId, request.getId());
        }
        if (null != request.getParentId()) {
            queryWrapper.eq(DictInfoPo::getParentId, request.getParentId());
        }
        if (StrUtil.isNotBlank(request.getCode())) {
            queryWrapper.eq(DictInfoPo::getCode, request.getCode());
        }
        if (StrUtil.isNotBlank(request.getName())) {
            queryWrapper.eq(DictInfoPo::getName, request.getName());
        }
        if (null != request.getStatus()) {
            queryWrapper.eq(DictInfoPo::getStatus, request.getStatus());
        }
        return queryWrapper;
    }
}
