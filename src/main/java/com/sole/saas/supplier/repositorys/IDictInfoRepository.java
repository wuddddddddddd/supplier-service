package com.sole.saas.supplier.repositorys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
public interface IDictInfoRepository extends IService<DictInfoPo> {


    List<DictInfoPo> getListByParams(DictInfoRequest request);
}
