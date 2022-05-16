package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBuyerUserMapper;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.repositorys.ISupplierBuyerUserRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-05-16
 */
@Repository
public class SupplierBuyerUserRepositoryImpl extends ServiceImpl<SupplierBuyerUserMapper, SupplierBuyerUserPo> implements ISupplierBuyerUserRepository {

    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBuyerUserPo entity) {
        return super.save(entity);
    }

    @Override
    @BaseData(fill = OperatorType.UPDATE)
    public boolean updateById(SupplierBuyerUserPo entity) {
        return super.updateById(entity);
    }
}
