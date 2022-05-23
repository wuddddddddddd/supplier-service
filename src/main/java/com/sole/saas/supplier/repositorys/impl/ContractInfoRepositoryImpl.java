package com.sole.saas.supplier.repositorys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.ContractInfoMapper;
import com.sole.saas.supplier.models.po.ContractInfoPo;
import com.sole.saas.supplier.models.request.ContractInfoRequest;
import com.sole.saas.supplier.repositorys.IContractInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Repository
public class ContractInfoRepositoryImpl extends ServiceImpl<ContractInfoMapper, ContractInfoPo> implements IContractInfoRepository {

    private final ContractInfoMapper contractInfoMapper;

    public ContractInfoRepositoryImpl(ContractInfoMapper contractInfoMapper) {
        this.contractInfoMapper = contractInfoMapper;
    }


    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(ContractInfoPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(ContractInfoPo entity) {
        return super.updateById(entity);
    }

    @Override
    public int updateByOneParams(SFunction<ContractInfoPo, ?> updateColumn, Object updateValue,
                                 SFunction<ContractInfoPo, ?> conditionColumn, Object conditionValue) {
        final LambdaUpdateWrapper<ContractInfoPo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(updateColumn, updateValue);
        updateWrapper.eq(conditionColumn, conditionValue);
        return contractInfoMapper.update(null, updateWrapper);
    }

    @Override
    public Page<ContractInfoPo> getPageByParams(Page<ContractInfoPo> page, ContractInfoRequest request) {
        final LambdaQueryWrapper<ContractInfoPo> queryWrapper = this.getQueryWrapper(request);
        return contractInfoMapper.selectPage(page, queryWrapper);
    }




    /**
     * @description 通用非空查询条件.
     * @author wjd
     * @date 2022/5/16
     * @param request 条件
     * @return 查询条件组装结果
     */
    private LambdaQueryWrapper<ContractInfoPo> getQueryWrapper(ContractInfoRequest request) {
        LambdaQueryWrapper<ContractInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != request.getId()) {
            queryWrapper.eq(ContractInfoPo::getId, request.getId());
        }
        if (null != request.getSupplierId()) {
            queryWrapper.eq(ContractInfoPo::getSupplierId, request.getSupplierId());
        }
        if (StrUtil.isNotBlank(request.getName())) {
            queryWrapper.eq(ContractInfoPo::getName, request.getName());
        }
        return queryWrapper;
    }
}
