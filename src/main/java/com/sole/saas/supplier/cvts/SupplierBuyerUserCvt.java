package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierBuyerUserLogPo;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
import com.sole.saas.supplier.models.response.SupplierBuyerUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description 供应商所属采购员实体转换.
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierBuyerUserCvt {
    SupplierBuyerUserCvt INSTANCE = Mappers.getMapper(SupplierBuyerUserCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    SupplierBuyerUserPo requestToPo(SupplierBuyerUserRequest request);

    /**
     * @description po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的response实体
     */
    SupplierBuyerUserResponse poToResponse(SupplierBuyerUserPo po);

    /**
     * @description po实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的log实体
     */
    SupplierBuyerUserLogPo poToLogPo(SupplierBuyerUserPo po);

    /**
     * @description request实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的log实体
     */
    SupplierBuyerUserLogPo requestToLogPo(SupplierBuyerUserRequest request);

    /**
     * @description log实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的response实体
     */
    SupplierBuyerUserResponse logPoToResponse(SupplierBuyerUserLogPo logPo);

    /**
     * @description log实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的po实体
     */
    SupplierBuyerUserPo logPoToPo(SupplierBuyerUserLogPo logPo);
}
