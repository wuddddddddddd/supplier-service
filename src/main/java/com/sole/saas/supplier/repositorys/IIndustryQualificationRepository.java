package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.IndustryQualificationPo;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;

/**
 * @author wjd
 * @description 行业资质信息数据仓储层接口.
 * @date 2022-05-21
 */
public interface IIndustryQualificationRepository extends IService<IndustryQualificationPo> {


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
    int updateByOneParams(SFunction<IndustryQualificationPo, ?> updateColumn, Object updateValue,
                          SFunction<IndustryQualificationPo, ?> conditionColumn, Object conditionValue);

    /**
     * @description 根据自定义条件查询结果集带分页
     * @author wjd
     * @date 2022/5/25
     * @param page 分页条件
     * @param request 查询条件
     * @return 行业资质信息结果集带分页
     */
    Page<IndustryQualificationPo> getPageByParams(Page<IndustryQualificationPo> page, IndustryQualificationRequest request);
}
