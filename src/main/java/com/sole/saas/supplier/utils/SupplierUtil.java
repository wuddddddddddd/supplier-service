package com.sole.saas.supplier.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
import com.sole.saas.supplier.services.impl.CheckOpinionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wjd
 * @description 供应商通用工具.
 * @date 2022-05-19
 */
@Component
public class SupplierUtil {
    private static final Logger logger = LoggerFactory.getLogger(SupplierUtil.class);

    private final IDictInfoRepository dictInfoRepository;

    public SupplierUtil(IDictInfoRepository dictInfoRepository) {
        this.dictInfoRepository = dictInfoRepository;
    }

    /**
      * @description 供应商信息&供应商记录信息通用信息组装.
      * @author wjd
      * @date 2022/5/25
      * @param
      * @return void
      */
    public void getSupplierPageInfo(List<SupplierPageResponse> list) {
        logger.info("[供应商信息组装]");
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
