package com.sole.saas.supplier.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.common.utils.RedisUtils;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.constant.OpinionTypeEnum;
import com.sole.saas.supplier.constant.SupplierConstant;
import com.sole.saas.supplier.cvts.*;
import com.sole.saas.supplier.models.po.*;
import com.sole.saas.supplier.models.request.*;
import com.sole.saas.supplier.models.response.*;
import com.sole.saas.supplier.repositorys.*;
import com.sole.saas.supplier.services.ISupplierLogService;
import com.sole.saas.supplier.utils.SupplierUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-19
 */
@Service
public class SupplierLogServiceImpl implements ISupplierLogService {
    private static final Logger logger = LoggerFactory.getLogger(SupplierLogServiceImpl.class);

    private final ISupplierBasicInfoRepository supplierBasicInfoRepository;

    private final IQualificationInfoRepository qualificationInfoRepository;

    private final ISupplierIndustryRepository supplierIndustryRepository;

    private final IRegisterInfoRepository registerInfoRepository;

    private final ISupplierUserInfoRepository supplierUserInfoRepository;

    private final ISupplierBuyerUserRepository supplierBuyerUserRepository;

    private final ISupplierBasicInfoLogRepository supplierBasicInfoLogRepository;

    private final IQualificationInfoLogRepository qualificationInfoLogRepository;

    private final ISupplierIndustryLogRepository supplierIndustryLogRepository;

    private final IRegisterInfoLogRepository registerInfoLogRepository;

    private final ISupplierUserInfoLogRepository supplierUserInfoLogRepository;

    private final ISupplierBuyerUserLogRepository supplierBuyerUserLogRepository;

    private final ICheckOpinionRepository checkOpinionRepository;

    private final RedisUtils redisUtils;

    private final SupplierUtil supplierUtil;

    public SupplierLogServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                                  ISupplierIndustryRepository supplierIndustryRepository, IRegisterInfoRepository registerInfoRepository,
                                  ISupplierUserInfoRepository supplierUserInfoRepository, ISupplierBuyerUserRepository supplierBuyerUserRepository,
                                  ISupplierBasicInfoLogRepository supplierBasicInfoLogRepository, IQualificationInfoLogRepository qualificationInfoLogRepository,
                                  ISupplierIndustryLogRepository supplierIndustryLogRepository, IRegisterInfoLogRepository registerInfoLogRepository,
                                  ISupplierUserInfoLogRepository supplierUserInfoLogRepository, ISupplierBuyerUserLogRepository supplierBuyerUserLogRepository,
                                  ICheckOpinionRepository checkOpinionRepository, RedisUtils redisUtils,
                                  SupplierUtil supplierUtil) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
        this.supplierIndustryRepository = supplierIndustryRepository;
        this.registerInfoRepository = registerInfoRepository;
        this.supplierUserInfoRepository = supplierUserInfoRepository;
        this.supplierBuyerUserRepository = supplierBuyerUserRepository;
        this.supplierBasicInfoLogRepository = supplierBasicInfoLogRepository;
        this.qualificationInfoLogRepository = qualificationInfoLogRepository;
        this.supplierIndustryLogRepository = supplierIndustryLogRepository;
        this.registerInfoLogRepository = registerInfoLogRepository;
        this.supplierUserInfoLogRepository = supplierUserInfoLogRepository;
        this.supplierBuyerUserLogRepository = supplierBuyerUserLogRepository;
        this.checkOpinionRepository = checkOpinionRepository;
        this.redisUtils = redisUtils;
        this.supplierUtil = supplierUtil;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initCreateSupplier(InitSupplierRequest request) {
        logger.info("[初始化创建简要供应商]---供应商名称为{}", request.getSupplierName());
        // 添加供应商信息并标识为创建中状态
        SupplierBasicInfoPo basicInfoPo = new SupplierBasicInfoPo();
        basicInfoPo.setName(request.getSupplierName());
        basicInfoPo.setBusinessStatus(BusinessStatusEnum.CREATE_ING.getCode());
        supplierBasicInfoRepository.save(basicInfoPo);
        final Long supplierId = basicInfoPo.getId();
        // 保存日志信息
        final SupplierBasicInfoLogPo supplierBasicInfoLogPo = SupplierBasicInfoCvt.INSTANCE.poToLogPo(basicInfoPo);
        supplierBasicInfoLogPo.setSupplierId(supplierId);
        supplierBasicInfoLogRepository.save(supplierBasicInfoLogPo);


        // 添加资质信息中的统一社会信用代码
        QualificationInfoPo qualificationInfoPo = new QualificationInfoPo();
        qualificationInfoPo.setSupplierId(supplierId);
        qualificationInfoPo.setCreditCode(request.getCreditCode());
        qualificationInfoRepository.save(qualificationInfoPo);
        // 保存日志信息
        final QualificationInfoLogPo qualificationInfoLogPo = QualificationInfoCvt.INSTANCE.poToLogPo(qualificationInfoPo);
        qualificationInfoLogRepository.save(qualificationInfoLogPo);

        // 添加供应商联系人
        SupplierUserInfoPo supplierUserInfoPo = new SupplierUserInfoPo();
        supplierUserInfoPo.setSupplierId(supplierId);
        supplierUserInfoPo.setName(request.getUserName());
        supplierUserInfoPo.setTelephone(request.getUserTelephone());
        supplierUserInfoPo.setEmail(request.getUserEmail());
        supplierUserInfoRepository.save(supplierUserInfoPo);
        // 保存日志信息
        final SupplierUserInfoLogPo supplierUserInfoLogPo = SupplierUserInfoCvt.INSTANCE.poToLogPo(supplierUserInfoPo);
        supplierUserInfoLogRepository.save(supplierUserInfoLogPo);


        // 添加采购员信息
        SupplierBuyerUserPo supplierBuyerUserPo = new SupplierBuyerUserPo();
        supplierBuyerUserPo.setSupplierId(supplierId);
        supplierBuyerUserPo.setBuyerUserId(request.getBuyerUserId());
        supplierBuyerUserRepository.save(supplierBuyerUserPo);
        // 保存日志信息
        final SupplierBuyerUserLogPo supplierBuyerUserLogPo = SupplierBuyerUserCvt.INSTANCE.poToLogPo(supplierBuyerUserPo);
        supplierBuyerUserLogRepository.save(supplierBuyerUserLogPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSupplier(SupplierRequest request, boolean isDraft) {
        final Long supplierId = request.getSupplierId();
        logger.info("[完善待审核供应商信息保存]---供应商ID为{}", supplierId);

        // 供应商基础信息更新
        // 将历史记录更新为逻辑删除状态
        supplierBasicInfoLogRepository.updateByOneParams(SupplierBasicInfoLogPo::getStatus, Constant.STATUS_DEL,
                SupplierBasicInfoLogPo::getSupplierId, supplierId);
        // 保存新信息
        final SupplierBasicInfoRequest basicInfoRequest = request.getBasicInfoRequest();
        final SupplierBasicInfoLogPo basicInfoLogPo = SupplierBasicInfoCvt.INSTANCE.requestToLogPo(basicInfoRequest);
        basicInfoLogPo.setId(null);
        basicInfoLogPo.setSupplierId(supplierId);
        basicInfoLogPo.setBusinessStatus(isDraft ? BusinessStatusEnum.DRAFT.getCode() : BusinessStatusEnum.IN_PROCESS.getCode());
        if (!isDraft && StrUtil.isBlank(basicInfoLogPo.getCode())) {
            // 供应商编码: GYS+创建年月日+6位随机数字
            final long code = redisUtils.incr("SUPPLIER_CODE", 1);
            String format = String.format("%06d", code);
            String supplierCode = SupplierConstant.SUPPLIER_CODE_KEY + DateUtil.today() + format;
            basicInfoLogPo.setCode(supplierCode);
        }
        supplierBasicInfoLogRepository.save(basicInfoLogPo);

        // 主营行业信息更新
        supplierIndustryLogRepository.updateByOneParams(SupplierIndustryLogPo::getStatus, Constant.STATUS_DEL,
                SupplierIndustryLogPo::getSupplierId, supplierId);
        final List<Long> industryList = request.getIndustryList();
        if (CollectionUtil.isNotEmpty(industryList)) {
            List<SupplierIndustryLogPo> supplierIndustryLogPoList = new ArrayList<>();
            for (Long industryId : industryList) {
                SupplierIndustryLogPo industryLogPo = new SupplierIndustryLogPo();
                industryLogPo.setSupplierId(supplierId);
                industryLogPo.setIndustryId(industryId);
                supplierIndustryLogPoList.add(industryLogPo);
            }
            supplierIndustryLogRepository.saveBatch(supplierIndustryLogPoList);
        }

        // 供应商资质信息更新
        qualificationInfoLogRepository.updateByOneParams(QualificationInfoLogPo::getStatus, Constant.STATUS_DEL,
                QualificationInfoLogPo::getSupplierId, supplierId);
        final QualificationInfoRequest qualificationInfoRequest = request.getQualificationInfoRequest();
        final QualificationInfoLogPo qualificationInfoLogPo = QualificationInfoCvt.INSTANCE.requestToLog(qualificationInfoRequest);
        qualificationInfoLogPo.setId(null);
        qualificationInfoLogPo.setSupplierId(supplierId);
        qualificationInfoLogRepository.save(qualificationInfoLogPo);

        // 公司注册信息更新
        registerInfoLogRepository.updateByOneParams(RegisterInfoLogPo::getStatus, Constant.STATUS_DEL,
                RegisterInfoLogPo::getSupplierId, supplierId);
        final RegisterInfoRequest registerInfoRequest = request.getRegisterInfoRequest();
        final RegisterInfoLogPo registerInfoLogPo = RegisterInfoCvt.INSTANCE.requestToLogPo(registerInfoRequest);
        registerInfoLogPo.setId(null);
        registerInfoLogPo.setSupplierId(supplierId);
        registerInfoLogRepository.save(registerInfoLogPo);

        // 供应商联系人信息更新
        supplierUserInfoLogRepository.updateByOneParams(SupplierUserInfoLogPo::getSupplierId, Constant.STATUS_DEL,
                SupplierUserInfoLogPo::getSupplierId, supplierId);
        final SupplierUserInfoRequest supplierUserInfoRequest = request.getSupplierUserInfoRequest();
        final SupplierUserInfoLogPo supplierUserInfoLogPo = SupplierUserInfoCvt.INSTANCE.requestToLogPo(supplierUserInfoRequest);
        supplierUserInfoLogPo.setId(null);
        supplierUserInfoLogPo.setSupplierId(supplierId);
        supplierUserInfoLogRepository.save(supplierUserInfoLogPo);

        // 采购员信息更新
        supplierBuyerUserLogRepository.updateByOneParams(SupplierBuyerUserLogPo::getStatus, Constant.STATUS_DEL,
                SupplierBuyerUserLogPo::getSupplierId, supplierId);
        final SupplierBuyerUserRequest supplierBuyerUserRequest = request.getSupplierBuyerUserRequest();
        final SupplierBuyerUserLogPo supplierBuyerUserLogPo = SupplierBuyerUserCvt.INSTANCE.requestToLogPo(supplierBuyerUserRequest);
        supplierBuyerUserLogRepository.save(supplierBuyerUserLogPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delSupplier(Long supplierId) {
        logger.info("[删除供应商]---供应商ID为{}", supplierId);
        // 标记主表为删除状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getStatus, Constant.STATUS_DEL,
                SupplierBasicInfoPo::getId, supplierId);
        // 标记记录表为删除状态
        supplierBasicInfoLogRepository.updateByOneParams(SupplierBasicInfoLogPo::getStatus, Constant.STATUS_DEL,
                SupplierBasicInfoLogPo::getSupplierId, supplierId);
    }

    @Override
    public SupplierResponse getSupplierLogBySupplierId(Long supplierId) {
        logger.info("[获取供应商记录信息]---供应商ID为{}", supplierId);
        SupplierResponse response = new SupplierResponse();

        // 基础信息
        SupplierBasicInfoRequest supplierBasicInfoRequest = new SupplierBasicInfoRequest();
        supplierBasicInfoRequest.setSupplierId(supplierId);
        supplierBasicInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierBasicInfoLogPo basicInfoLogPo = supplierBasicInfoLogRepository.getByParams(supplierBasicInfoRequest);
        ExceptionUtils.error(null == basicInfoLogPo)
                .errorMessage(null, "根据供应商ID{}未获取到记录信息", supplierId);
        final SupplierBasicInfoResponse supplierBasicInfoResponse = SupplierBasicInfoCvt.INSTANCE.logPoToResponse(basicInfoLogPo);
        response.setBasicInfoResponse(supplierBasicInfoResponse);

        // 主营行业信息
        SupplierIndustryRequest industryRequest = new SupplierIndustryRequest();
        industryRequest.setSupplierId(supplierId);
        industryRequest.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierIndustryLogPo> industryLogPoList = supplierIndustryLogRepository.getListByParams(industryRequest);
        final List<SupplierIndustryResponse> industryResponseList = SupplierIndustryCvt.INSTANCE.logPoToResponseBatch(industryLogPoList);
        response.setIndustryResponseList(industryResponseList);

        // 供应商资质信息
        QualificationInfoRequest qualificationInfoRequest = new QualificationInfoRequest();
        qualificationInfoRequest.setSupplierId(supplierId);
        qualificationInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final QualificationInfoLogPo qualificationInfoLogPo = qualificationInfoLogRepository.getByParams(qualificationInfoRequest);
        final QualificationInfoResponse qualificationInfoResponse = QualificationInfoCvt.INSTANCE.logPoToResponse(qualificationInfoLogPo);
        response.setQualificationInfoResponse(qualificationInfoResponse);

        // 公司注册信息
        RegisterInfoRequest registerInfoRequest = new RegisterInfoRequest();
        registerInfoRequest.setSupplierId(supplierId);
        registerInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final RegisterInfoLogPo registerInfoLogPo = registerInfoLogRepository.getByParams(registerInfoRequest);
        final RegisterInfoResponse registerInfoResponse = RegisterInfoCvt.INSTANCE.logPoToResponse(registerInfoLogPo);
        response.setRegisterInfoResponse(registerInfoResponse);

        // 供应商联系人信息
        SupplierUserInfoRequest userInfoRequest = new SupplierUserInfoRequest();
        userInfoRequest.setSupplierId(supplierId);
        userInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierUserInfoLogPo userInfoLogPo = supplierUserInfoLogRepository.getByParams(userInfoRequest);
        final SupplierUserInfoResponse userInfoResponse = SupplierUserInfoCvt.INSTANCE.logPoToResponse(userInfoLogPo);
        response.setSupplierUserInfoResponse(userInfoResponse);

        // 采购员信息
        SupplierBuyerUserRequest buyerUserRequest = new SupplierBuyerUserRequest();
        buyerUserRequest.setSupplierId(supplierId);
        buyerUserRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierBuyerUserLogPo buyerUserLogPo = supplierBuyerUserLogRepository.getByParams(buyerUserRequest);
        final SupplierBuyerUserResponse buyerUserResponse = SupplierBuyerUserCvt.INSTANCE.logPoToResponse(buyerUserLogPo);
        response.setSupplierBuyerUserResponse(buyerUserResponse);

        return response;
    }

    @Override
    public IPage<SupplierPageResponse> getSupplierLogPageByParams(SupplierPageRequest request) {
        logger.info("[查询供应商记录分页信息]");
        Page<SupplierBasicInfoPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final IPage<SupplierPageResponse> pageResponse = supplierBasicInfoLogRepository.getCustomerLogPage(page, request);
        if (pageResponse.getTotal() <= 0) {
            return pageResponse;
        }
        // 分页信息组装
        final List<SupplierPageResponse> list = pageResponse.getRecords();
        supplierUtil.getSupplierPageInfo(list);
        return pageResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkApproval(Long supplierId, Long currentUserId) {
        logger.info("[供应商审批通过]---供应商ID为{}", supplierId);

        // 修改记录表业务状态为审批通过
        supplierBasicInfoLogRepository.updateByOneParams(SupplierBasicInfoLogPo::getBusinessStatus, BusinessStatusEnum.PROCESS_SUCCESS.getCode(),
                SupplierBasicInfoLogPo::getSupplierId, supplierId);

        // 将记录表数据更新到主表
        // 基础信息表数据更新
        SupplierBasicInfoRequest supplierBasicInfoRequest = new SupplierBasicInfoRequest();
        supplierBasicInfoRequest.setSupplierId(supplierId);
        supplierBasicInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierBasicInfoLogPo basicInfoLogPo = supplierBasicInfoLogRepository.getByParams(supplierBasicInfoRequest);
        ExceptionUtils.error(null == basicInfoLogPo)
                .errorMessage(null, "根据供应商ID{}未获取到记录信息", supplierId);
        final SupplierBasicInfoPo basicInfoPo = SupplierBasicInfoCvt.INSTANCE.logPoToPo(basicInfoLogPo);
        basicInfoPo.setId(supplierId);
        supplierBasicInfoRepository.save(basicInfoPo);

        // 主营行业信息更新
        supplierIndustryRepository.updateByOneParams(SupplierIndustryPo::getStatus, Constant.STATUS_DEL,
                SupplierIndustryPo::getSupplierId, supplierId);
        SupplierIndustryRequest industryRequest = new SupplierIndustryRequest();
        industryRequest.setSupplierId(supplierId);
        industryRequest.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierIndustryLogPo> industryLogPoList = supplierIndustryLogRepository.getListByParams(industryRequest);
        final List<SupplierIndustryPo> industryPoList = SupplierIndustryCvt.INSTANCE.logPoToPoBatch(industryLogPoList);
        supplierIndustryRepository.saveBatch(industryPoList);

        // 供应商资质信息更新
        qualificationInfoRepository.updateByOneParams(QualificationInfoPo::getStatus, Constant.STATUS_DEL,
                QualificationInfoPo::getSupplierId, supplierId);
        QualificationInfoRequest qualificationInfoRequest = new QualificationInfoRequest();
        qualificationInfoRequest.setSupplierId(supplierId);
        qualificationInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final QualificationInfoLogPo qualificationInfoLogPo = qualificationInfoLogRepository.getByParams(qualificationInfoRequest);
        final QualificationInfoPo qualificationInfoPo = QualificationInfoCvt.INSTANCE.logPoToPo(qualificationInfoLogPo);
        qualificationInfoRepository.save(qualificationInfoPo);

        // 公司注册信息更新
        RegisterInfoRequest registerInfoRequest = new RegisterInfoRequest();
        registerInfoRequest.setSupplierId(supplierId);
        registerInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final RegisterInfoLogPo registerInfoLogPo = registerInfoLogRepository.getByParams(registerInfoRequest);
        final RegisterInfoPo registerInfoPo = RegisterInfoCvt.INSTANCE.logPoToPo(registerInfoLogPo);
        registerInfoRepository.save(registerInfoPo);

        // 供应商联系人信息更新
        SupplierUserInfoRequest userInfoRequest = new SupplierUserInfoRequest();
        userInfoRequest.setSupplierId(supplierId);
        userInfoRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierUserInfoLogPo userInfoLogPo = supplierUserInfoLogRepository.getByParams(userInfoRequest);
        final SupplierUserInfoPo supplierUserInfoPo = SupplierUserInfoCvt.INSTANCE.logPoToPo(userInfoLogPo);
        supplierUserInfoRepository.save(supplierUserInfoPo);

        // 采购员信息更新
        SupplierBuyerUserRequest buyerUserRequest = new SupplierBuyerUserRequest();
        buyerUserRequest.setSupplierId(supplierId);
        buyerUserRequest.setStatus(Constant.STATUS_NOT_DEL);
        final SupplierBuyerUserLogPo buyerUserLogPo = supplierBuyerUserLogRepository.getByParams(buyerUserRequest);
        final SupplierBuyerUserPo buyerUserPo = SupplierBuyerUserCvt.INSTANCE.logPoToPo(buyerUserLogPo);
        supplierBuyerUserRepository.save(buyerUserPo);

        // 保存审批信息
        CheckOpinionPo checkOpinionPo = new CheckOpinionPo();
        checkOpinionPo.setType(OpinionTypeEnum.SUPPLIER.getCode());
        checkOpinionPo.setBusinessId(supplierId);
        checkOpinionPo.setAssigneeId(currentUserId);
        checkOpinionPo.setOpinionType(BusinessStatusEnum.PROCESS_SUCCESS.getCode());
        checkOpinionRepository.save(checkOpinionPo);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkReject(Long supplierId, String reason, Long currentUserId) {
        logger.info("[供应商审批驳回]---供应商ID为{}", supplierId);
        // 修改状态为驳回状态
        supplierBasicInfoLogRepository.updateByOneParams(SupplierBasicInfoLogPo::getBusinessStatus, BusinessStatusEnum.PROCESS_REJECT.getCode(),
                SupplierBasicInfoLogPo::getSupplierId, supplierId);
        // 保存审批信息
        CheckOpinionPo checkOpinionPo = new CheckOpinionPo();
        checkOpinionPo.setType(OpinionTypeEnum.SUPPLIER.getCode());
        checkOpinionPo.setBusinessId(supplierId);
        checkOpinionPo.setAssigneeId(currentUserId);
        checkOpinionPo.setOpinionType(BusinessStatusEnum.PROCESS_REJECT.getCode());
        checkOpinionPo.setRemark(reason);
        checkOpinionRepository.save(checkOpinionPo);
    }
}
