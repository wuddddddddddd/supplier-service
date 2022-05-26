package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.OutlinePo;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.OutLinePageResponse;
import com.sole.saas.supplier.models.response.OutlineResponse;

import java.util.List;

/**
 * @author wjd
 * @description 供应商违规信息仓储层接口.
 * @date 2022-05-26
 */
public interface IOutlineRepository extends IService<OutlinePo> {

    /**
     * @description 根据某个条件列修改某列值.
     * @author wjd
     * @date 2022/5/16
     * @param updateColumn 待修改的列
     * @param updateValue 待修改的值
     * @param conditionColumn 条件列
     * @param conditionValue 条件值
     * @return int 受影响行数
     */
    int updateByOneParams(SFunction<OutlinePo, ?> updateColumn, Object updateValue,
                          SFunction<OutlinePo, ?> conditionColumn, Object conditionValue);

    /**
     * @author wjd
     * @description 违规供应商信息集带分页.
     * @date 2022/4/29
     * @param page 分页信息
     * @Param request 条件信息
     * @return 违规供应商信息集带分页
     **/
    IPage<OutLinePageResponse> getCustomerPage(Page<OutlinePo> page, OutlinePageRequest request);

    /**
      * @description 根据自定义条件查询违规供应商信息集.
      * @author wjd
      * @date 2022/5/26
      * @param request 查询条件
      * @return 违规供应商信息集
      */
    List<OutlinePo> getListByParams(OutlineRequest request);
}
