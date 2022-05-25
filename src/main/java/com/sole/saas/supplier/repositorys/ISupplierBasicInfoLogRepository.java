package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.SupplierBasicInfoLogPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.SupplierBasicInfoRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 供应商基础信息记录数据仓储层接口.
 * @Author wjd
 * @date 2022/5/10
 */
public interface ISupplierBasicInfoLogRepository extends IService<SupplierBasicInfoLogPo> {

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
    int updateByOneParams(SFunction<SupplierBasicInfoLogPo, ?> updateColumn, Object updateValue,
                          SFunction<SupplierBasicInfoLogPo, ?> conditionColumn, Object conditionValue);

    /**
     * @description 根据自定义条件查询.
     * @author wjd
     * @date 2022/5/25
     * @param request 自定义查询条件
     * @return 供应商基础信息记录
     */
    SupplierBasicInfoLogPo getByParams(SupplierBasicInfoRequest request);

    /**
     * @description 根据自定义条件查询供应商带分页记录信息.
     * @author wjd
     * @date 2022/5/17
     * @param page 分页信息
     * @param request 查询条件
     * @return 带分页的供应商信息集
     */
    IPage<SupplierPageResponse> getCustomerLogPage(@Param("page") Page<SupplierBasicInfoPo> page, @Param("qo") SupplierPageRequest request);

}
