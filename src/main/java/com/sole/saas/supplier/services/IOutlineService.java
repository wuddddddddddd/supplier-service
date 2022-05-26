package com.sole.saas.supplier.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.OutLinePageResponse;
import com.sole.saas.supplier.models.response.OutlineResponse;

import java.util.List;

/**
 * @author wjd
 * @description 供应商违规信息服务层接口.
 * @date 2022-05-26
 */
public interface IOutlineService {

    /**
     * @author wjd
     * @description 新增供应商违规信息.
     * @date 2022/5/26
     * @param request 待新增的供应商违规信息
     **/
    void addOutLine(OutlineRequest request);

    /**
     * @author wjd
     * @description 根据ID修改业务状态.
     * @date 2022/5/26
     * @param id 待修改的ID
     * @Param businessStatus 业务状态
     **/
    void updateBusinessStatus(Long id, Integer businessStatus);

    /**
     * @author wjd
     * @description 根据过滤条件获取结果集带分页.
     * @date 2022/5/5
     * @param request 查询条件
     * @return 结果集带分页
     **/
    IPage<OutLinePageResponse> getCustomerPage(OutlinePageRequest request);

    /**
      * @description 根据自定义条件查询违规供应商信息集.
      * @author wjd
      * @date 2022/5/26
      * @param request 查询条件
      * @return 违规供应商信息集
      */
    List<OutlineResponse> getListByParams(OutlineRequest request);
}
