package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;

/**
 * @author wjd
 * @description 供应商记录信息服务层接口.
 * @date 2022-05-19
 */
public interface ISupplierLogService {

    /**
      * @description 初始化创建供应商.
      * @author wjd
      * @date 2022/5/25
      * @param request 待新增的实体
      */
    void initCreateSupplier(InitSupplierRequest request);

    /**
      * @description 完善&修改供应商.
      * @author wjd
      * @date 2022/5/25
      * @param request 待完善&修改的实体
      */
    void addSupplier(SupplierRequest request, boolean isDraft);

    /**
      * @description 删除供应商.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      */
    void delSupplier(Long supplierId);

    /**
      * @description 根据供应商主键ID获取供应商记录信息.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      * @return 供应商记录信息
      */
    SupplierResponse getSupplierLogBySupplierId(Long supplierId);

    /**
      * @description 根据自定义条件查询供应商记录信息集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商记录信息集带分页
      */
    IPage<SupplierPageResponse> getSupplierLogPageByParams(SupplierPageRequest request);

    /**
      * @description 供应商审批通过.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      * @param currentUserId 当前审批人
      */
    void checkApproval(Long supplierId, Long currentUserId);

    /**
     * @description 供应商审批驳回.
     * @author wjd
     * @date 2022/5/25
     * @param supplierId 供应商主键ID
     * @param reason 驳回原因
     * @param currentUserId 当前审批人
     */
    void checkReject(Long supplierId, String reason, Long currentUserId);
}
