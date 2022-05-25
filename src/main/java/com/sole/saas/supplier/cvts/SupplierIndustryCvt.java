package com.sole.saas.supplier.cvts;

import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.response.SupplierIndustryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wjd
 * @description 供应商主营行业关联关系实体转换.
 * @date 2022-05-19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierIndustryCvt {
    SupplierIndustryCvt INSTANCE = Mappers.getMapper(SupplierIndustryCvt.class);

    /**
     * @description po实体集转换成response实体集.
     * @author wjd
     * @date 2022/5/24
     * @param poList 待转换的po实体集
     * @return 转换后的response实体集
     */
    List<SupplierIndustryResponse> poToResponseBatch(List<SupplierIndustryPo> poList);

    /**
     * @description log实体集转换成response实体集.
     * @author wjd
     * @date 2022/5/24
     * @param logPoList 待转换的log实体集
     * @return 转换后的response实体集
     */
    List<SupplierIndustryResponse> logPoToResponseBatch(List<SupplierIndustryLogPo> logPoList);

    /**
     * @description log实体集转换成po实体集.
     * @author wjd
     * @date 2022/5/24
     * @param logPoList 待转换的log实体集
     * @return 转换后的po实体集
     */
    List<SupplierIndustryPo> logPoToPoBatch(List<SupplierIndustryLogPo> logPoList);
}
