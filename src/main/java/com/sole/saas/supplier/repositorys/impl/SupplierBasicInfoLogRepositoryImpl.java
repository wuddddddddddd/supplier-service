package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.common.aops.BaseData;
import com.sole.saas.common.constant.OperatorType;
import com.sole.saas.supplier.mappers.SupplierBasicInfoLogMapper;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoLogRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
@Repository
public class SupplierBasicInfoLogRepositoryImpl extends ServiceImpl<SupplierBasicInfoLogMapper, SupplierBasicInfoLogPo> implements ISupplierBasicInfoLogRepository {


    @Override
    @BaseData(fill = OperatorType.INSERT)
    public boolean save(SupplierBasicInfoLogPo entity) {
        return super.save(entity);
    }

    @Override
    public int updateByOneParams(SFunction<SupplierBasicInfoLogPo, ?> updateColumn, Object updateValue, SFunction<SupplierBasicInfoLogPo, ?> conditionColumn, Object conditionValue) {
        return 0;
    }
}
