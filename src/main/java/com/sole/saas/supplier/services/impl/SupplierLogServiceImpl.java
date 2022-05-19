package com.sole.saas.supplier.services.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.utils.RedisUtils;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.constant.SupplierConstant;
import com.sole.saas.supplier.cvts.*;
import com.sole.saas.supplier.models.po.*;
import com.sole.saas.supplier.models.request.*;
import com.sole.saas.supplier.repositorys.*;
import com.sole.saas.supplier.services.ISupplierLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final IRegisterInfoRepository registerInfoRepository;

    private final ISupplierUserInfoRepository supplierUserInfoRepository;

    private final ISupplierBuyerUserRepository supplierBuyerUserRepository;

    private final ISupplierBasicInfoLogRepository supplierBasicInfoLogRepository;

    private final IQualificationInfoLogRepository qualificationInfoLogRepository;

    private final IRegisterInfoLogRepository registerInfoLogRepository;

    private final ISupplierUserInfoLogRepository supplierUserInfoLogRepository;

    private final ISupplierBuyerUserLogRepository supplierBuyerUserLogRepository;

    private final RedisUtils redisUtils;

    public SupplierLogServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                                  IRegisterInfoRepository registerInfoRepository, ISupplierUserInfoRepository supplierUserInfoRepository,
                                  ISupplierBuyerUserRepository supplierBuyerUserRepository, ISupplierBasicInfoLogRepository supplierBasicInfoLogRepository,
                                  IQualificationInfoLogRepository qualificationInfoLogRepository, IRegisterInfoLogRepository registerInfoLogRepository,
                                  ISupplierUserInfoLogRepository supplierUserInfoLogRepository, ISupplierBuyerUserLogRepository supplierBuyerUserLogRepository,
                                  RedisUtils redisUtils) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
        this.registerInfoRepository = registerInfoRepository;
        this.supplierUserInfoRepository = supplierUserInfoRepository;
        this.supplierBuyerUserRepository = supplierBuyerUserRepository;
        this.supplierBasicInfoLogRepository = supplierBasicInfoLogRepository;
        this.qualificationInfoLogRepository = qualificationInfoLogRepository;
        this.registerInfoLogRepository = registerInfoLogRepository;
        this.supplierUserInfoLogRepository = supplierUserInfoLogRepository;
        this.supplierBuyerUserLogRepository = supplierBuyerUserLogRepository;
        this.redisUtils = redisUtils;
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

        // 创建一条空的公司注册信息
        RegisterInfoPo registerInfoPo = new RegisterInfoPo();
        registerInfoPo.setSupplierId(supplierId);
        registerInfoRepository.save(registerInfoPo);
        // 保存日志信息
        final RegisterInfoLogPo registerInfoLogPo = RegisterInfoCvt.INSTANCE.poToLogPo(registerInfoPo);
        registerInfoLogRepository.save(registerInfoLogPo);

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
}
