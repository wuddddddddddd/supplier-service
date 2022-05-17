package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierBasicInfoRepository extends IService<SupplierBasicInfoPo> {

    int updateByOneParams(SFunction<SupplierBasicInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierBasicInfoPo, ?> conditionColumn, Object conditionValue);

    /**
     * @description 根据自定义条件查询供应商带分页信息.
     * @author wjd
     * @date 2022/5/17
     * @param page 分页信息
     * @param request 查询条件
     * @return 带分页的供应商信息集
     */
    IPage<SupplierPageResponse> getCustomerPage(@Param("page") Page<SupplierBasicInfoPo> page, @Param("qo") SupplierPageRequest request);
}
