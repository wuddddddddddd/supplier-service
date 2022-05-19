package com.sole.saas.supplier.services;

import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
public interface ISupplierLogService {

    void initCreateSupplier(InitSupplierRequest request);

    void addSupplier(SupplierRequest request, boolean isDraft);
}
