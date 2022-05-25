package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.request.QualificationInfoRequest;

/**
 * @author wjd
 * @description 供应商资质信息数据仓储层接口.
 * @date 2022-05-16
 */
public interface IQualificationInfoRepository extends IService<QualificationInfoPo> {


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
    int updateByOneParams(SFunction<QualificationInfoPo, ?> updateColumn, Object updateValue,
                          SFunction<QualificationInfoPo, ?> conditionColumn, Object conditionValue);

    /**
     * @description 根据自定义条件查询.
     * @author wjd
     * @date 2022/5/25
     * @param request 自定义查询条件
     * @return 供应商资质信息
     */
    QualificationInfoPo getByParams(QualificationInfoRequest request);
}
