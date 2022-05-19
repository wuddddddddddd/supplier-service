package com.sole.saas.supplier.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
@Component
public class SupplierUtil {

    private final IDictInfoRepository dictInfoRepository;

    public SupplierUtil(IDictInfoRepository dictInfoRepository) {
        this.dictInfoRepository = dictInfoRepository;
    }

    public void getSupplierPageInfo(List<SupplierPageResponse> list) {
        // 经营类型ID集
        Set<Long> manageTypeIdSet = new HashSet<>();
        for (SupplierPageResponse response : list) {
            manageTypeIdSet.add(response.getManageTypeId());
        }
        // 经营类型<经营类型ID, 业务字典集>
        Map<Long, DictInfoPo> dictMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(manageTypeIdSet)) {
            DictInfoRequest dictInfoRequest = new DictInfoRequest();
            dictInfoRequest.setIdList(new ArrayList<>(manageTypeIdSet));
            final List<DictInfoPo> dictInfoPoList = dictInfoRepository.getListByParams(dictInfoRequest);
            if (CollectionUtil.isNotEmpty(dictInfoPoList)) {
                dictMap = dictInfoPoList.stream().collect(Collectors.toMap(DictInfoPo::getId, e -> e, (k1, k2) -> k1));
            }
        }

        // 组装数据
        for (SupplierPageResponse response : list) {
            final Long manageTypeId = response.getManageTypeId();
            if (dictMap.containsKey(manageTypeId)) {
                final DictInfoPo dictInfoPo = dictMap.get(manageTypeId);
                response.setManageTypeName(dictInfoPo.getName());
            }
        }
    }
}
