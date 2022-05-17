package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierInfoService {

    void initCreateSupplier(InitSupplierRequest request);

    void addSupplier(SupplierRequest request, boolean isDraft);

    SupplierResponse getSupplierInfoById(Long supplierId);

    void delSupplier(Long supplierId);

    IPage<SupplierPageResponse> getSupplierPageByParams(SupplierPageRequest request);
}
