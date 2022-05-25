package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.models.response.SupplierBasicInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description 供应商基础信息实体转换.
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierBasicInfoCvt {
    SupplierBasicInfoCvt INSTANCE = Mappers.getMapper(SupplierBasicInfoCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    SupplierBasicInfoPo requestToPo(SupplierBasicInfoRequest request);

    /**
     * @description po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的response实体
     */
    SupplierBasicInfoResponse poToResponse(SupplierBasicInfoPo po);

    /**
     * @description po实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的log实体
     */
    SupplierBasicInfoLogPo poToLogPo(SupplierBasicInfoPo po);

    /**
     * @description request实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的log实体
     */
    SupplierBasicInfoLogPo requestToLogPo(SupplierBasicInfoRequest request);

    /**
     * @description log实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的response实体
     */
    SupplierBasicInfoResponse logPoToResponse(SupplierBasicInfoLogPo logPo);

    /**
     * @description log实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的po实体
     */
    SupplierBasicInfoPo logPoToPo(SupplierBasicInfoLogPo logPo);

}
