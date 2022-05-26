package com.sole.saas.supplier.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.OutlineCvt;
import com.sole.saas.supplier.models.po.DictInfoPo;
import com.sole.saas.supplier.models.po.OutlinePo;
import com.sole.saas.supplier.models.request.DictInfoRequest;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.*;
import com.sole.saas.supplier.repositorys.IDictInfoRepository;
import com.sole.saas.supplier.repositorys.IOutlineRepository;
import com.sole.saas.supplier.services.IOutlineService;
import com.sole.saas.supplier.utils.OrgUtil;
import com.sole.saas.supplier.utils.SupplierUtil;
import com.sole.saas.supplier.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wjd
 * @description 供应商违规信息服务层接口实现.
 * @date 2022-05-26
 */
@Service
public class OutlineServiceImpl implements IOutlineService {
    private static final Logger logger = LoggerFactory.getLogger(OutlineServiceImpl.class);


    private final IOutlineRepository outlineRepository;

    private final IDictInfoRepository dictInfoRepository;

    private final OrgUtil orgUtil;

    private final UserUtil userUtil;

    private final SupplierUtil supplierUtil;

    public OutlineServiceImpl(IOutlineRepository outlineRepository, IDictInfoRepository dictInfoRepository,
                              OrgUtil orgUtil, UserUtil userUtil, SupplierUtil supplierUtil) {
        this.outlineRepository = outlineRepository;
        this.dictInfoRepository = dictInfoRepository;
        this.orgUtil = orgUtil;
        this.userUtil = userUtil;
        this.supplierUtil = supplierUtil;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOutLine(OutlineRequest request) {
        logger.info("[新增违规供应商信息]---供应商ID为{}", request.getSupplierId());
        final OutlinePo outlinePo = OutlineCvt.INSTANCE.requestToPo(request);
        outlinePo.setBusinessStatus(BusinessStatusEnum.UN_CORRECT.getCode());
        outlineRepository.save(outlinePo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBusinessStatus(Long id, Integer businessStatus) {
        logger.info("[修改违规供应商业务状态]---其中违规ID为{},待修改的业务状态为{}", id, businessStatus);
        outlineRepository.updateByOneParams(OutlinePo::getBusinessStatus, businessStatus,
                OutlinePo::getId, id);
    }

    @Override
    public IPage<OutLinePageResponse> getCustomerPage(OutlinePageRequest request) {
        logger.info("[修改违规供应商分页信息查询]");
        Page<OutlinePo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final IPage<OutLinePageResponse> pageResponse = outlineRepository.getCustomerPage(page, request);
        if (pageResponse.getTotal() <= 0) {
            return pageResponse;
        }
        final List<OutLinePageResponse> records = pageResponse.getRecords();
        this.getPageInfo(records);
        return pageResponse;
    }

    @Override
    public List<OutlineResponse> getListByParams(OutlineRequest request) {
        logger.info("[根据自定义条件查询违规供应商信息集]");
        List<OutlineResponse> list = new ArrayList<>();
        final List<OutlinePo> outlinePoList = outlineRepository.getListByParams(request);
        if (CollectionUtil.isEmpty(outlinePoList)) {
            return list;
        }
        list = OutlineCvt.INSTANCE.poToResponseBatch(outlinePoList);
        this.getDetailInfo(list);
        return list;
    }

    /**
      * @description 自定义分页信息组装.
      * @author wjd
      * @date 2022/5/26
      * @param list 待组装的信息体
      */
    private void getPageInfo(List<OutLinePageResponse> list) {
        // 经营类型ID集
        Set<Long> manageTypeIdSet = new HashSet<>();
        // 区域ID集
        Set<Long> areaIdSet = new HashSet<>();
        // 采购员ID集
        Set<Integer> userIdSet = new HashSet<>();
        // 供应商ID集
        Set<Long> supplierIdSet = new HashSet<>();
        for (OutLinePageResponse response : list) {
            manageTypeIdSet.add(response.getManageTypeId());
            areaIdSet.add(response.getProvinceId());
            areaIdSet.add(response.getCityId());
            areaIdSet.add(response.getDistrictId());
            if (null != response.getBuyerUserId()) {
                final int userId = response.getBuyerUserId().intValue();
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
        Map<Long, List<SupplierIndustryResponse>> industryMap = supplierUtil.getIndustryMap(supplierIdSet);

        for (OutLinePageResponse response : list) {
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
      * @description 违规信息集组装.
      * @author wjd
      * @date 2022/5/26
      * @param list 待组装的信息体
      */
    private void getDetailInfo(List<OutlineResponse> list) {
        // 获取违规类型ID集
        final List<Long> dictList = list.stream().map(OutlineResponse::getTypeId).collect(Collectors.toList());

        // 根据违规类型ID集获取对应的业务字典
        Map<Long, DictInfoPo> dictMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(dictList)) {
            final List<DictInfoPo> dictInfoPoList = dictInfoRepository.listByIds(dictList);
            if (CollectionUtil.isNotEmpty(dictInfoPoList)) {
                dictMap = dictInfoPoList.stream().collect(Collectors.toMap(DictInfoPo::getId, e -> e, (k1, k2) -> k1));
            }
        }

        // 信息组装
        for (OutlineResponse outlineResponse : list) {
            final Long typeId = outlineResponse.getTypeId();
            if (null != typeId && dictMap.containsKey(typeId)) {
                final DictInfoPo dictInfoPo = dictMap.get(typeId);
                outlineResponse.setTypeName(dictInfoPo.getName());
            }
        }
    }
}
