package com.sole.saas.supplier.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.supplier.cvts.SupplierIndustryCvt;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.po.SupplierIndustryLogPo;
import com.sole.saas.supplier.models.po.SupplierIndustryPo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.models.request.SupplierIndustryRequest;
import com.sole.saas.supplier.models.response.CommonAreaResponse;
import com.sole.saas.supplier.models.response.SupplierIndustryResponse;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.UserResponse;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
import com.sole.saas.supplier.repositorys.ISupplierIndustryLogRepository;
import com.sole.saas.supplier.repositorys.ISupplierIndustryRepository;
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

    private final ISupplierIndustryLogRepository supplierIndustryLogRepository;

    private final ISupplierIndustryRepository supplierIndustryRepository;

    public SupplierUtil(IDictInfoRepository dictInfoRepository, OrgUtil orgUtil, UserUtil userUtil,
                        ISupplierIndustryLogRepository supplierIndustryLogRepository, ISupplierIndustryRepository supplierIndustryRepository) {
        this.dictInfoRepository = dictInfoRepository;
        this.orgUtil = orgUtil;
        this.userUtil = userUtil;
        this.supplierIndustryLogRepository = supplierIndustryLogRepository;
        this.supplierIndustryRepository = supplierIndustryRepository;
    }

    /**
      * @description 供应商信息&供应商记录信息通用信息组装.
      * @author wjd
      * @date 2022/5/25
      * @param list 待组装的信息
      */
    public void getSupplierPageInfo(List<SupplierPageResponse> list, boolean isLog) {
        logger.info("[供应商信息组装]");
        // 经营类型ID集
        Set<Long> manageTypeIdSet = new HashSet<>();
        // 区域ID集
        Set<Long> areaIdSet = new HashSet<>();
        // 采购员ID集
        Set<Integer> userIdSet = new HashSet<>();
        // 供应商ID集
        Set<Long> supplierIdSet = new HashSet<>();
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
            supplierIdSet.add(response.getSupplierId());
        }
        // 经营类型<经营类型ID, 业务字典集>
        DictInfoRequest dictInfoRequest = new DictInfoRequest();
        dictInfoRequest.setIdList(new ArrayList<>(manageTypeIdSet));
        Map<Long, DictInfoPo> dictMap = dictInfoRepository.getMapByParams(dictInfoRequest);

        // 区域信息
        Map<Long, CommonAreaResponse> areaMap = orgUtil.getAreaMapByIdList(new ArrayList<>(areaIdSet));


        // 采购员信息
        Map<Long, UserResponse> userMap = userUtil.getUserMapByIdList(new ArrayList<>(userIdSet));

        // 主营行业信息
        Map<Long, List<SupplierIndustryResponse>> industryMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(supplierIdSet)) {
            if (isLog) {
                industryMap = this.getIndustryLogMap(supplierIdSet);
            } else {
                industryMap = this.getIndustryMap(supplierIdSet);
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

            // 主营行业
            final Long supplierId = response.getSupplierId();
            if (null != supplierId && industryMap.containsKey(supplierId)) {
                final List<SupplierIndustryResponse> industryResponseList = industryMap.get(supplierId);
                final List<String> industryNameList = industryResponseList.stream().map(SupplierIndustryResponse::getIndustryName).collect(Collectors.toList());
                final String industryNames = StrUtil.join(",", industryNameList);
                response.setIndustryNames(industryNames);
            }
        }
    }


    /**
      * @description 根据供应商ID集查询出供应商关联主营行业记录信息并以供应商ID分组.
      * @author wjd
      * @date 2022/5/25
      * @param supplierIdSet 供应商ID集
      * @return 供应商关联主营行业记录信息
      */
    public Map<Long, List<SupplierIndustryResponse>> getIndustryLogMap(Set<Long> supplierIdSet) {
        Map<Long, List<SupplierIndustryResponse>> industryMap = new HashMap<>();

        SupplierIndustryRequest request = new SupplierIndustryRequest();
        request.setSupplierIdList(new ArrayList<>(supplierIdSet));
        request.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierIndustryLogPo> industryLogPoList = supplierIndustryLogRepository.getListByParams(request);
        if (CollectionUtil.isEmpty(industryLogPoList)) {
            return industryMap;
        }
        final List<SupplierIndustryResponse> industryResponseList = SupplierIndustryCvt.INSTANCE.logPoToResponseBatch(industryLogPoList);
        industryMap = industryResponseList.stream().collect(Collectors.groupingBy(SupplierIndustryResponse::getSupplierId));

        return industryMap;
    }

    /**
     * @description 根据供应商ID集查询出供应商关联主营行业信息并以供应商ID分组.
     * @author wjd
     * @date 2022/5/25
     * @param supplierIdSet 供应商ID集
     * @return 供应商关联主营行业信息
     */
    public Map<Long, List<SupplierIndustryResponse>> getIndustryMap(Set<Long> supplierIdSet) {
        Map<Long, List<SupplierIndustryResponse>> industryMap = new HashMap<>();
        SupplierIndustryRequest request = new SupplierIndustryRequest();
        request.setSupplierIdList(new ArrayList<>(supplierIdSet));
        request.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierIndustryPo> industryPoList = supplierIndustryRepository.getListByParams(request);
        if (CollectionUtil.isEmpty(industryPoList)) {
            return industryMap;
        }
        final List<SupplierIndustryResponse> industryResponseList = SupplierIndustryCvt.INSTANCE.poToResponseBatch(industryPoList);
        industryMap = industryResponseList.stream().collect(Collectors.groupingBy(SupplierIndustryResponse::getSupplierId));

        return industryMap;
    }
}
