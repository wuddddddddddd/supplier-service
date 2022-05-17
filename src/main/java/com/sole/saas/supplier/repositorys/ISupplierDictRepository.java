package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierDictPo;
import com.sole.saas.supplier.models.request.SupplierDictRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
public interface ISupplierDictRepository extends IService<SupplierDictPo> {



    int updateOneByParams(SFunction<SupplierDictPo, ?> updateColumn, Object updateValue,
                          SupplierDictRequest request);

    List<SupplierDictPo> getListByParams(SupplierDictRequest request);
}
