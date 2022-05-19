package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierIndustryMapper;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.repositorys.ISupplierIndustryRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
@Repository
public class SupplierIndustryRepositoryImpl extends ServiceImpl<SupplierIndustryMapper, SupplierIndustryPo> implements ISupplierIndustryRepository {

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean saveBatch(Collection<SupplierIndustryPo> entityList) {
        return super.saveBatch(entityList);
    }
}
