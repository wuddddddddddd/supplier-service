package com.sole.saas.supplier.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.constant.*;
import com.sole.saas.supplier.cvts.*;
import com.sole.saas.supplier.models.po.*;
import com.sole.saas.supplier.models.request.*;
import com.sole.saas.supplier.models.response.*;
import com.sole.saas.supplier.repositorys.*;
import com.sole.saas.supplier.services.ISupplierInfoService;
import com.sole.saas.supplier.utils.SupplierUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description: 初始化创建供应商服务层实现.
 * @Author wjd
 * @date 2022/5/10
 */
@Service
public class SupplierServiceImpl implements ISupplierInfoService {
    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    private final ISupplierBasicInfoRepository supplierBasicInfoRepository;

    private final IQualificationInfoRepository qualificationInfoRepository;

    private final IRegisterInfoRepository registerInfoRepository;

    private final ISupplierUserInfoRepository supplierUserInfoRepository;

    private final ISupplierBuyerUserRepository supplierBuyerUserRepository;

    private final ISupplierIndustryRepository supplierIndustryRepository;

    private final IBusinessHistoryRepository businessHistoryRepository;

    private final SupplierUtil supplierUtil;


    public SupplierServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                               IRegisterInfoRepository registerInfoRepository, ISupplierUserInfoRepository supplierUserInfoRepository,
                               ISupplierBuyerUserRepository supplierBuyerUserRepository, ISupplierIndustryRepository supplierIndustryRepository,
                               IBusinessHistoryRepository businessHistoryRepository, SupplierUtil supplierUtil) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
        this.registerInfoRepository = registerInfoRepository;
        this.supplierUserInfoRepository = supplierUserInfoRepository;
        this.supplierBuyerUserRepository = supplierBuyerUserRepository;
        this.supplierIndustryRepository = supplierIndustryRepository;
        this.businessHistoryRepository = businessHistoryRepository;
        this.supplierUtil = supplierUtil;
    }

    @Override
    public SupplierResponse getSupplierInfoById(Long supplierId) {
        logger.info("[获取供应商详细信息]---供应商ID为{}", supplierId);

        SupplierResponse response = new SupplierResponse();
        // 获取供应商基础信息
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                        .errorMessage(null, "根据供应商ID{}未获取到对应的供应商信息", supplierId);
        final SupplierBasicInfoResponse basicInfoResponse = SupplierBasicInfoCvt.INSTANCE.poToResponse(basicInfoPo);
        response.setBasicInfoResponse(basicInfoResponse);

        // 主营行业信息
        SupplierIndustryRequest industryRequest = new SupplierIndustryRequest();
        industryRequest.setSupplierId(supplierId);
        industryRequest.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierIndustryPo> industryPoList = supplierIndustryRepository.getListByParams(industryRequest);
        final List<SupplierIndustryResponse> industryResponseList = SupplierIndustryCvt.INSTANCE.poToResponseBatch(industryPoList);
        response.setIndustryResponseList(industryResponseList);


        // 资质信息
        QualificationInfoRequest qualificationInfoRequest = new QualificationInfoRequest();
        qualificationInfoRequest.setSupplierId(supplierId);
        qualificationInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final QualificationInfoPo qualificationInfo = qualificationInfoRepository.getByParams(qualificationInfoRequest);
        final QualificationInfoResponse qualificationInfoResponse = QualificationInfoCvt.INSTANCE.poToResponse(qualificationInfo);
        response.setQualificationInfoResponse(qualificationInfoResponse);

        // 供应商联系人信息
        SupplierUserInfoRequest userInfoRequest = new SupplierUserInfoRequest();
        userInfoRequest.setSupplierId(supplierId);
        userInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierUserInfoPo userInfoPo = supplierUserInfoRepository.getByParams(userInfoRequest);
        final SupplierUserInfoResponse userInfoResponse = SupplierUserInfoCvt.INSTANCE.poToResponse(userInfoPo);
        response.setSupplierUserInfoResponse(userInfoResponse);

        // 公司注册信息
        RegisterInfoRequest registerInfoRequest = new RegisterInfoRequest();
        registerInfoRequest.setSupplierId(supplierId);
        registerInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final RegisterInfoPo registerInfoPo = registerInfoRepository.getByParams(registerInfoRequest);
        final RegisterInfoResponse registerInfoResponse = RegisterInfoCvt.INSTANCE.poToResponse(registerInfoPo);
        response.setRegisterInfoResponse(registerInfoResponse);

        // 采购员信息
        SupplierBuyerUserRequest buyerUserRequest = new SupplierBuyerUserRequest();
        buyerUserRequest.setBuyerUserId(supplierId);
        buyerUserRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierBuyerUserPo buyerUserPo = supplierBuyerUserRepository.getByParams(buyerUserRequest);
        final SupplierBuyerUserResponse buyerUserResponse = SupplierBuyerUserCvt.INSTANCE.poToResponse(buyerUserPo);
        response.setSupplierBuyerUserResponse(buyerUserResponse);

        return response;
    }

    @Override
    public IPage<SupplierPageResponse> getSupplierPageByParams(SupplierPageRequest request) {
        logger.info("[查询供应商分页信息]");
        Page<SupplierBasicInfoPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final IPage<SupplierPageResponse> pageResponse = supplierBasicInfoRepository.getCustomerPage(page, request);
        if (pageResponse.getTotal() <= 0) {
            return pageResponse;
        }
        // 分页信息组装
        final List<SupplierPageResponse> list = pageResponse.getRecords();
        supplierUtil.getSupplierPageInfo(list, false);
        return pageResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remarkSelfSupport(Long supplierId) {
        logger.info("[供应商标记自营]---供应商ID为{}", supplierId);
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getSelfSupportType,
                SelfSupportEnum.OWNER, SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelSelfSupport(Long supplierId) {
        logger.info("[供应商取消自营]---供应商ID为{}", supplierId);
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getSelfSupportType, SelfSupportEnum.OTHER.getCode(),
                SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopCooperation(Long supplierId, String reason) {
        logger.info("[供应商终止合作]----供应商ID为{}", supplierId);
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                .errorMessage(null, "根据供应商ID{}未查询到信息", supplierId);

        // 修改业务状态为终止合作状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                BusinessStatusEnum.STOP_COOPERATION.getCode(), SupplierBasicInfoPo::getId, supplierId);

        // 历史记录表记录业务状态变更
        // 根据供应商ID逻辑删除历史信息
        businessHistoryRepository.updateByOneParams(BusinessHistoryPo::getStatus, Constant.STATUS_DEL,
                BusinessHistoryPo::getBusinessId, supplierId);
        // 新增变更记录
        BusinessHistoryPo businessHistoryPo = new BusinessHistoryPo();
        businessHistoryPo.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryPo.setBusinessId(supplierId);
        businessHistoryPo.setBusinessStatus(BusinessStatusEnum.STOP_COOPERATION.getCode());
        businessHistoryPo.setOldBusinessStatus(basicInfoPo.getBusinessStatus());
        businessHistoryPo.setRemark(reason);
        businessHistoryRepository.save(businessHistoryPo);

        // 调用接口,所有商品下架
        /*final boolean isTrue = null == response || response.getError().getCode() != HttpStatus.OK.value();
        ExceptionUtils.error(isTrue)
                .errorMessage(null, "商品下架异常");*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recoverCooperation(Long supplierId) {
        logger.info("[供应商恢复合作]----供应商ID为{}", supplierId);

        // 获取历史业务记录表信息
        BusinessHistoryRequest businessHistoryRequest = new BusinessHistoryRequest();
        businessHistoryRequest.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryRequest.setBusinessId(supplierId);
        businessHistoryRequest.setStatus(Constant.STATUS_NOT_DEL);
        final BusinessHistoryPo historyPo = businessHistoryRepository.getByParams(businessHistoryRequest);
        ExceptionUtils.error(null == historyPo)
                .errorMessage(null, "根据变更类型{}供应商ID{}未查询到历史业务变更信息", HistoryTypeEnum.SUPPLIER.getCode(), supplierId);

        // 恢复终止合作前的业务状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                historyPo.getOldBusinessStatus(), SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinBlack(Long supplierId, String reason) {
        logger.info("[供应商加黑]----供应商ID为{}", supplierId);
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                .errorMessage(null, "根据供应商ID{}未查询到信息", supplierId);

        // 修改业务状态为加黑状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                BusinessStatusEnum.BLACK.getCode(), SupplierBasicInfoPo::getId, supplierId);

        // 历史记录表记录业务状态变更
        // 根据供应商ID逻辑删除历史信息
        businessHistoryRepository.updateByOneParams(BusinessHistoryPo::getStatus, Constant.STATUS_DEL,
                BusinessHistoryPo::getBusinessId, supplierId);
        // 新增变更记录
        BusinessHistoryPo businessHistoryPo = new BusinessHistoryPo();
        businessHistoryPo.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryPo.setBusinessId(supplierId);
        businessHistoryPo.setBusinessStatus(BusinessStatusEnum.BLACK.getCode());
        businessHistoryPo.setOldBusinessStatus(basicInfoPo.getBusinessStatus());
        businessHistoryPo.setRemark(reason);
        businessHistoryRepository.save(businessHistoryPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBuyerUser(SupplierBuyerUserRequest request) {
        logger.info("[分配供应商]----分配采购员为{}", request.getBuyerUserId());
        SupplierBuyerUserRequest buyerUserRequest = new SupplierBuyerUserRequest();
        buyerUserRequest.setSupplierIdList(request.getSupplierIdList());
        buyerUserRequest.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierBuyerUserPo> buyerUserPoList = supplierBuyerUserRepository.getListByParams(buyerUserRequest);
        if (CollectionUtil.isEmpty(buyerUserPoList)) {
            return;
        }
        for (SupplierBuyerUserPo buyerUserPo : buyerUserPoList) {
            final Long oldBuyerUserId = buyerUserPo.getBuyerUserId();
            buyerUserPo.setOldBuyerUserId(oldBuyerUserId);
            buyerUserPo.setBuyerUserId(request.getBuyerUserId());
        }
        supplierBuyerUserRepository.updateBatchById(buyerUserPoList);
    }
}
