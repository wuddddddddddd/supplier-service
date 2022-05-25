package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.RegisterInfoLogPo;
import com.sole.saas.supplier.models.po.RegisterInfoPo;
import com.sole.saas.supplier.models.request.RegisterInfoRequest;
import com.sole.saas.supplier.models.response.RegisterInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description 供应商注册信息实体转换.
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterInfoCvt {
    RegisterInfoCvt INSTANCE = Mappers.getMapper(RegisterInfoCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    RegisterInfoPo requestToPo(RegisterInfoRequest request);

    /**
     * @description po实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param po 待转换的po实体
     * @return 转换后的response实体
     */
    RegisterInfoResponse poToResponse(RegisterInfoPo po);

    /**
     * @description request实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的log实体
     */
    RegisterInfoLogPo requestToLogPo(RegisterInfoRequest request);

    /**
     * @description log实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的response实体
     */
    RegisterInfoResponse logPoToResponse(RegisterInfoLogPo logPo);

    /**
     * @description log实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的po实体
     */
    RegisterInfoPo logPoToPo(RegisterInfoLogPo logPo);


}
