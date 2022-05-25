package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.QualificationInfoLogPo;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;
import com.sole.saas.supplier.models.response.QualificationInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description 供应商资质信息实体转换.
 * @date 2022-05-16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QualificationInfoCvt {
    QualificationInfoCvt INSTANCE = Mappers.getMapper(QualificationInfoCvt.class);

    /**
     * @description request实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的po实体
     */
    QualificationInfoPo requestToPo(QualificationInfoRequest request);

    /**
      * @description po实体转换成response实体.
      * @author wjd
      * @date 2022/5/24
      * @param po 待转换的po实体
      * @return 转换后的response实体
      */
    QualificationInfoResponse poToResponse(QualificationInfoPo po);

    /**
      * @description po实体转换成log实体.
      * @author wjd
      * @date 2022/5/24
      * @param po 待转换的po实体
      * @return 转换后的log实体
      */
    QualificationInfoLogPo poToLogPo(QualificationInfoPo po);

    /**
     * @description request实体转换成log实体.
     * @author wjd
     * @date 2022/5/24
     * @param request 待转换的request实体
     * @return 转换后的log实体
     */
    QualificationInfoLogPo requestToLog(QualificationInfoRequest request);

    /**
     * @description log实体转换成response实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的response实体
     */
    QualificationInfoResponse logPoToResponse(QualificationInfoLogPo logPo);

    /**
     * @description log实体转换成po实体.
     * @author wjd
     * @date 2022/5/24
     * @param logPo 待转换的log实体
     * @return 转换后的po实体
     */
    QualificationInfoPo logPoToPo(QualificationInfoLogPo logPo);


}
