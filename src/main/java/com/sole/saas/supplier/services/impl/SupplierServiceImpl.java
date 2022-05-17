package com.sole.saas.supplier.services.impl;

import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.*;
import com.sole.saas.supplier.models.po.*;
import com.sole.saas.supplier.models.request.*;
import com.sole.saas.supplier.models.response.*;
import com.sole.saas.supplier.repositorys.*;
import com.sole.saas.supplier.services.ISupplierInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: TODO
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

    public SupplierServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                               IRegisterInfoRepository registerInfoRepository, ISupplierUserInfoRepository supplierUserInfoRepository,
                               ISupplierBuyerUserRepository supplierBuyerUserRepository) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
        this.registerInfoRepository = registerInfoRepository;
        this.supplierUserInfoRepository = supplierUserInfoRepository;
        this.supplierBuyerUserRepository = supplierBuyerUserRepository;
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

        // 添加资质信息中的统一社会信用代码
        QualificationInfoPo qualificationInfoPo = new QualificationInfoPo();
        qualificationInfoPo.setSupplierId(supplierId);
        qualificationInfoPo.setCreditCode(request.getCreditCode());
        qualificationInfoRepository.save(qualificationInfoPo);

        // 创建一条空的公司注册信息
        RegisterInfoPo registerInfoPo = new RegisterInfoPo();
        registerInfoPo.setSupplierId(supplierId);
        qualificationInfoRepository.save(qualificationInfoPo);

        // 添加供应商联系人
        SupplierUserInfoPo supplierUserInfoPo = new SupplierUserInfoPo();
        supplierUserInfoPo.setSupplierId(supplierId);
        supplierUserInfoPo.setName(request.getUserName());
        supplierUserInfoPo.setTelephone(request.getUserTelephone());
        supplierUserInfoPo.setEmail(request.getUserEmail());
        supplierUserInfoRepository.save(supplierUserInfoPo);

        // 添加采购员信息
        SupplierBuyerUserPo supplierBuyerUserPo = new SupplierBuyerUserPo();
        supplierBuyerUserPo.setSupplierId(supplierId);
        supplierBuyerUserPo.setBuyerUserId(request.getBuyerUserId());
        supplierBuyerUserRepository.save(supplierBuyerUserPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSupplier(SupplierRequest request, boolean isDraft) {
        logger.info("[完善供应商信息]---供应商ID为{}", request.getBasicInfoRequest().getId());

        // 供应商基础信息
        final SupplierBasicInfoRequest basicInfoRequest = request.getBasicInfoRequest();
        final SupplierBasicInfoPo basicInfoPo = SupplierBasicInfoCvt.INSTANCE.requestToPo(basicInfoRequest);
        basicInfoPo.setBusinessStatus(isDraft ? BusinessStatusEnum.DRAFT.getCode() : BusinessStatusEnum.IN_PROCESS.getCode());
        supplierBasicInfoRepository.updateById(basicInfoPo);

        // 资质信息
        final QualificationInfoRequest qualificationInfoRequest = request.getQualificationInfoRequest();
        final QualificationInfoPo qualificationInfoPo = QualificationInfoCvt.INSTANCE.requestToPo(qualificationInfoRequest);
        qualificationInfoRepository.updateById(qualificationInfoPo);

        // 供应商联系人信息
        final SupplierUserInfoRequest userInfoRequest = request.getSupplierUserInfoRequest();
        final SupplierUserInfoPo supplierUserInfoPo = SupplierUserInfoCvt.INSTANCE.requestToPo(userInfoRequest);
        supplierUserInfoRepository.updateById(supplierUserInfoPo);

        // 公司注册信息
        final RegisterInfoRequest registerInfoRequest = request.getRegisterInfoRequest();
        final RegisterInfoPo registerInfoPo = RegisterInfoCvt.INSTANCE.requestToPo(registerInfoRequest);
        registerInfoRepository.save(registerInfoPo);

        // 采购员信息
        final SupplierBuyerUserRequest buyerUserRequest = request.getSupplierBuyerUserRequest();
        final SupplierBuyerUserPo buyerUserPo = SupplierBuyerUserCvt.INSTANCE.requestToPo(buyerUserRequest);
        supplierBuyerUserRepository.updateById(buyerUserPo);
    }

    @Override
    public SupplierResponse getSupplierInfoById(Long supplierId) {

        SupplierResponse response = new SupplierResponse();
        // 获取供应商基础信息
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                        .errorMessage(null, "根据供应商ID{}未获取到对应的供应商信息", supplierId);
        final SupplierBasicInfoResponse basicInfoResponse = SupplierBasicInfoCvt.INSTANCE.poToResponse(basicInfoPo);
        response.setBasicInfoResponse(basicInfoResponse);

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
}
