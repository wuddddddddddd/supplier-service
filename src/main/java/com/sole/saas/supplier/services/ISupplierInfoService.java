package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;

/**
 * @description: 初始化创建供应商服务层接口.
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierInfoService {

    /**
      * @description 根据供应商主键ID查询供应商信息.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      * @return 供应商信息
      */
    SupplierResponse getSupplierInfoById(Long supplierId);

    /**
      * @description 根据自定义条件查询供应商信息结果集带分页.
      * @author wjd
      * @date 2022/5/25
      * @param request 查询条件
      * @return 供应商信息结果集带分页
      */
    IPage<SupplierPageResponse> getSupplierPageByParams(SupplierPageRequest request);

    /**
      * @description 供应商标记自营.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      */
    void remarkSelfSupport(Long supplierId);

    /**
      * @description 供应商取消自营.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      */
    void cancelSelfSupport(Long supplierId);

    /**
      * @description 供应商终止合作.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      * @param reason 终止合作原因
      */
    void stopCooperation(Long supplierId, String reason);

    /**
      * @description 供应商恢复合作.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      */
    void recoverCooperation(Long supplierId);

    /**
      * @description 供应商加黑.
      * @author wjd
      * @date 2022/5/25
      * @param supplierId 供应商主键ID
      * @param reason 加黑原因
      */
    void joinBlack(Long supplierId, String reason);

    /**
      * @description 重新分配采购员.
      * @author wjd
      * @date 2022/5/25
      * @param request 重新分配请求信息
      */
    void updateBuyerUser(SupplierBuyerUserRequest request);
}
