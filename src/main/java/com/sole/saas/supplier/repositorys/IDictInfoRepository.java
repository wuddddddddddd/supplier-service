package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;

import java.util.List;
import java.util.Map;

/**
 * @author wjd
 * @description 业务字典信息数据仓储层接口.
 * @date 2022-05-17
 */
public interface IDictInfoRepository extends IService<DictInfoPo> {

    /**
     * @description 根据自定义条件查询结果集.
     * @author wjd
     * @date 2022/5/25
     * @param request 查询条件
     * @return 业务字典信息集
     */
    List<DictInfoPo> getListByParams(DictInfoRequest request);

    /**
      * @description 根据自定义条件查询结果集并转换成Map.
      * @author wjd
      * @date 2022/5/26
      * @param request 查询条件
      * @return 结果Map
      */
    Map<Long, DictInfoPo> getMapByParams(DictInfoRequest request);
}
