package com.sole.saas.supplier.services.impl;

import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.CheckOpinionCvt;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.po.SupplierBasicInfoPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;
import com.sole.saas.supplier.repositorys.ICheckOpinionRepository;
import com.sole.saas.supplier.repositorys.ISupplierBasicInfoRepository;
import com.sole.saas.supplier.services.ICheckOpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
@Service
public class CheckOpinionServiceImpl implements ICheckOpinionService {
    private static final Logger logger = LoggerFactory.getLogger(CheckOpinionServiceImpl.class);

    private final ISupplierBasicInfoRepository supplierBasicInfoRepository;

    private final ICheckOpinionRepository checkOpinionRepository;


    public CheckOpinionServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, ICheckOpinionRepository checkOpinionRepository) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.checkOpinionRepository = checkOpinionRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkApproval(CheckOpinionRequest request) {
        final Long supplierId = request.getAssigneeId();
        logger.info("[供应商审批通过]---供应商ID为{}", supplierId);
        // 修改供应商状态为审核通过
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus, BusinessStatusEnum.PROCESS_SUCCESS.getCode(),
                SupplierBasicInfoPo::getId, supplierId);

        // 记录审批信息
        final CheckOpinionPo checkOpinionPo = CheckOpinionCvt.INSTANCE.requestToPo(request);
        checkOpinionRepository.save(checkOpinionPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkReject(CheckOpinionRequest request) {
        final Long supplierId = request.getAssigneeId();
        logger.info("[供应商审批驳回]---供应商ID为{}", supplierId);
        // 修改供应商状态为驳回状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus, BusinessStatusEnum.PROCESS_REJECT.getCode(),
                SupplierBasicInfoPo::getId, supplierId);

        // 记录审批信息
        final CheckOpinionPo checkOpinionPo = CheckOpinionCvt.INSTANCE.requestToPo(request);
        checkOpinionRepository.save(checkOpinionPo);
    }


    @Override
    public List<CheckOpinionResponse> getOpinionListByParams(CheckOpinionRequest request) {
        logger.info("[根据条件查询审批信息集]");
        final List<CheckOpinionPo> checkOpinionPoList = checkOpinionRepository.getListByParams(request);
        return CheckOpinionCvt.INSTANCE.poToResponseBatch(checkOpinionPoList);
    }
}
