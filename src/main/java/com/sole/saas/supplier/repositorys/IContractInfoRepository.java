package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.ContractInfoPo;
import com.sole.saas.supplier.models.request.ContractInfoRequest;

/**
 * @author wjd
 * @description 供应商合同信息数据仓储层接口.
 * @date 2022-05-23
 */
public interface IContractInfoRepository extends IService<ContractInfoPo> {

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
    int updateByOneParams(SFunction<ContractInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<ContractInfoPo, ?> conditionColumn, Object conditionValue);

    /**
     * @description 根据自定义条件查询结果集带分页
     * @author wjd
     * @date 2022/5/25
     * @param page 分页条件
     * @param request 查询条件
     * @return 供应商合同信息结果集带分页
     */
    Page<ContractInfoPo> getPageByParams(Page<ContractInfoPo> page, ContractInfoRequest request);
}
