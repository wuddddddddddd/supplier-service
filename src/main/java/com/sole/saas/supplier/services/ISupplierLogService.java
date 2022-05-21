package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
public interface ISupplierLogService {

    void initCreateSupplier(InitSupplierRequest request);

    void addSupplier(SupplierRequest request, boolean isDraft);

    void delSupplier(Long supplierId);

    SupplierResponse getSupplierLogBySupplierId(Long supplierId);

    IPage<SupplierPageResponse> getSupplierLogPageByParams(SupplierPageRequest request);

    void checkApproval(Long supplierId, Long currentUserId);

    void checkReject(Long supplierId, String reason, Long currentUserId);
}
