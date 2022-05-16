package com.sole.saas.supplier.services.impl;

import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.models.po.QualificationInfoPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.po.SupplierBuyerUserPo;
import com.sole.saas.supplier.models.po.SupplierUserInfoPo;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.repositorys.IQualificationInfoRepository;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoRepository;
import com.sole.saas.supplier.repositorys.ISupplierBuyerUserRepository;
import com.sole.saas.supplier.repositorys.ISupplierUserInfoRepository;
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

    private final ISupplierUserInfoRepository supplierUserInfoRepository;

    private final ISupplierBuyerUserRepository supplierBuyerUserRepository;

    public SupplierServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                               ISupplierUserInfoRepository supplierUserInfoRepository, ISupplierBuyerUserRepository supplierBuyerUserRepository) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
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
}
