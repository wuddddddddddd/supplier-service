package com.sole.saas.supplier.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.supplier.models.po.OutlinePo;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.OutLinePageResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @author wjd
 * @description
 * @date 2022-05-26
 */
public interface OutlineMapper extends BaseMapper<OutlinePo> {

    /**
     * @author wjd
     * @description 违规供应商信息集带分页.
     * @date 2022/4/29
     * @param page 分页信息
     * @Param request 条件信息
     * @return 违规供应商信息集带分页
     **/
    IPage<OutLinePageResponse> getCustomerPage(@Param("page") Page<OutlinePo> page, @Param("qo") OutlinePageRequest request);
}
