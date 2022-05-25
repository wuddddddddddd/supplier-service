package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierUserInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.SupplierUserInfoRequest;
import com.sole.saas.supplier.models.response.SupplierUserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description 供应商联系人信息实体转换.
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierUserInfoCvt {
    SupplierUserInfoCvt INSTANCE = Mappers.getMapper(SupplierUserInfoCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    SupplierUserInfoPo requestToPo(SupplierUserInfoRequest request);

    /**
     * @description po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的response实体
     */
    SupplierUserInfoResponse poToResponse(SupplierUserInfoPo po);

    /**
     * @description po实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的log实体
     */
    SupplierUserInfoLogPo poToLogPo(SupplierUserInfoPo po);

    /**
     * @description request实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的log实体
     */
    SupplierUserInfoLogPo requestToLogPo(SupplierUserInfoRequest request);

    /**
     * @description log实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的response实体
     */
    SupplierUserInfoResponse logPoToResponse(SupplierUserInfoLogPo logPo);

    /**
     * @description log实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的po实体
     */
    SupplierUserInfoPo logPoToPo(SupplierUserInfoLogPo logPo);
}
