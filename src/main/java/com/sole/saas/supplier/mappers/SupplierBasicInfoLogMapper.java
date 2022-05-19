package com.sole.saas.supplier.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @description: TODO
 * @Author wjd
 * @date 2022/5/10
 */
public interface SupplierBasicInfoLogMapper extends BaseMapper<SupplierBasicInfoLogPo> {


    /**
     * @description 根据自定义条件查询供应商带分页信息(后续不使用改通过代码实现).
     * @author wjd
     * @date 2022/5/17
     * @param page 分页信息
     * @param request 查询条件
     * @return 带分页的供应商信息集
     */
    IPage<SupplierPageResponse> getCustomerLogPage(@Param("page") Page<SupplierBasicInfoPo> page, @Param("qo") SupplierPageRequest request);
}
