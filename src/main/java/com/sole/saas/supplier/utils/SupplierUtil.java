package com.sole.saas.supplier.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.models.response.CommonAreaResponse;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.UserResponse;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
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

    private final OrgUtil orgUtil;

    private final UserUtil userUtil;

    public SupplierUtil(IDictInfoRepository dictInfoRepository, OrgUtil orgUtil,
                        UserUtil userUtil) {
        this.dictInfoRepository = dictInfoRepository;
        this.orgUtil = orgUtil;
        this.userUtil = userUtil;
    }

    /**
      * @description 供应商信息&供应商记录信息通用信息组装.
      * @author wjd
      * @date 2022/5/25
      * @param list 待组装的信息
      */
    public void getSupplierPageInfo(List<SupplierPageResponse> list) {
        logger.info("[供应商信息组装]");
        // 经营类型ID集
        Set<Long> manageTypeIdSet = new HashSet<>();
        // 区域ID集
        Set<Long> areaIdSet = new HashSet<>();
        // 采购员ID集
        Set<Integer> userIdSet = new HashSet<>();

        for (SupplierPageResponse response : list) {
            manageTypeIdSet.add(response.getManageTypeId());

            areaIdSet.add(response.getProvinceId());
            areaIdSet.add(response.getCityId());
            areaIdSet.add(response.getDistrictId());

            if (null != response.getBuyerUserId()) {
                final int userId = response.getBuyerUserId().intValue();
                userIdSet.add(userId);
            }
            if (null != response.getOldBuyerUserId()) {
                final int userId = response.getOldBuyerUserId().intValue();
                userIdSet.add(userId);
            }

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

        // 区域信息
        Map<Long, CommonAreaResponse> areaMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(areaIdSet)) {
            final List<CommonAreaResponse> areaList = orgUtil.getAreaByIdList(new ArrayList<>(areaIdSet));
            if (CollectionUtil.isNotEmpty(areaList)) {
                areaMap = areaList.stream().collect(Collectors.toMap(CommonAreaResponse::getAreaId, e -> e, (k1, k2) -> k1));
            }
        }

        // 采购员信息
        Map<Long, UserResponse> userMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(userIdSet)) {
            final List<UserResponse> userList = userUtil.getUserListByIdList(new ArrayList<>(userIdSet));
            if (CollectionUtil.isNotEmpty(userList)) {
                userMap = userList.stream().collect(Collectors.toMap(UserResponse::getId, e -> e, (k1, k2) -> k1));
            }
        }

        // 组装数据
        for (SupplierPageResponse response : list) {
            // 经营类型
            final Long manageTypeId = response.getManageTypeId();
            if (null != manageTypeId && dictMap.containsKey(manageTypeId)) {
                final DictInfoPo dictInfoPo = dictMap.get(manageTypeId);
                response.setManageTypeName(dictInfoPo.getName());
            }

            // 区域
            final Long provinceId = response.getProvinceId();
            if (null != provinceId && areaMap.containsKey(provinceId)) {
                final CommonAreaResponse commonAreaResponse = areaMap.get(provinceId);
                response.setProvinceName(commonAreaResponse.getAreaName());
            }
            final Long cityId = response.getCityId();
            if (null != cityId && areaMap.containsKey(cityId)) {
                final CommonAreaResponse commonAreaResponse = areaMap.get(cityId);
                response.setCityName(commonAreaResponse.getAreaName());
            }
            final Long districtId = response.getDistrictId();
            if (null != districtId && areaMap.containsKey(districtId)) {
                final CommonAreaResponse commonAreaResponse = areaMap.get(districtId);
                response.setDistrictName(commonAreaResponse.getAreaName());
            }

            // 采购员
            final Long buyerUserId = response.getBuyerUserId();
            if (null != buyerUserId && userMap.containsKey(buyerUserId)) {
                final UserResponse userResponse = userMap.get(buyerUserId);
                response.setBuyerUserName(userResponse.getName());
            }
            // 原采购员
            final Long oldBuyerUserId = response.getOldBuyerUserId();
            if (null != oldBuyerUserId && userMap.containsKey(oldBuyerUserId)) {
                final UserResponse userResponse = userMap.get(oldBuyerUserId);
                response.setOldBuyerUserName(userResponse.getName());
            }

        }
    }
}
