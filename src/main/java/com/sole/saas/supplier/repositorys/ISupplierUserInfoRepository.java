package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;

/**
 * @author wjd
 * @description 供应商联系人信息数据仓储层接口.
 * @date 2022-05-16
 */
public interface ISupplierUserInfoRepository extends IService<SupplierUserInfoPo> {

    /**
     * @description 根据自定义条件查询.
     * @author wjd
     * @date 2022/5/25
     * @param request 自定义查询条件
     * @return 供应商联系人信息
     */
    SupplierUserInfoPo getByParams(SupplierUserInfoRequest request);
}
