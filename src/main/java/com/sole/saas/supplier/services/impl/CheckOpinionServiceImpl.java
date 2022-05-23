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
    public List<CheckOpinionResponse> getOpinionListByParams(CheckOpinionRequest request) {
        logger.info("[根据条件查询审批信息集]");
        final List<CheckOpinionPo> checkOpinionPoList = checkOpinionRepository.getListByParams(request);
        return CheckOpinionCvt.INSTANCE.poToResponseBatch(checkOpinionPoList);
    }
}
