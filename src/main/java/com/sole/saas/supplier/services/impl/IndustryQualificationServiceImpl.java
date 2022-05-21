package com.sole.saas.supplier.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.IndustryQualificationCvt;
import com.sole.saas.supplier.models.po.IndustryQualificationPo;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.models.response.IndustryQualificationResponse;
import com.sole.saas.supplier.repositorys.IIndustryQualificationRepository;
import com.sole.saas.supplier.services.IIndustryQualificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-21
 */
@Service
public class IndustryQualificationServiceImpl implements IIndustryQualificationService {
    private static final Logger logger = LoggerFactory.getLogger(IndustryQualificationServiceImpl.class);


    private final IIndustryQualificationRepository industryQualificationRepository;

    public IndustryQualificationServiceImpl(IIndustryQualificationRepository industryQualificationRepository) {
        this.industryQualificationRepository = industryQualificationRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIndustryQualification(IndustryQualificationRequest request) {
        logger.info("[新增行业资质信息]---供应商ID为{}", request.getSupplierId());
        // 实体转换
        final IndustryQualificationPo industryQualificationPo = IndustryQualificationCvt.INSTANCE.requestToPo(request);
        // 业务状态为审批中
        industryQualificationPo.setBusinessStatus(BusinessStatusEnum.IN_PROCESS.getCode());
        industryQualificationRepository.save(industryQualificationPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIndustryQualification(IndustryQualificationRequest request) {
        logger.info("[修改行业资质信息]---主键ID为{}", request.getId());
        // 实体转换
        final IndustryQualificationPo industryQualificationPo = IndustryQualificationCvt.INSTANCE.requestToPo(request);
        // 业务状态为审批中
        industryQualificationPo.setBusinessStatus(BusinessStatusEnum.IN_PROCESS.getCode());
        industryQualificationRepository.updateById(industryQualificationPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delIndustryQualification(Long id) {
        logger.info("[删除行业资质信息]---主键ID为{}", id);
        industryQualificationRepository.updateByOneParams(IndustryQualificationPo::getStatus, Constant.STATUS_DEL,
                IndustryQualificationPo::getId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkApproval(Long id) {
        logger.info("[行业资质信息审批通过]---主键ID为{}", id);
        industryQualificationRepository.updateByOneParams(IndustryQualificationPo::getBusinessStatus, BusinessStatusEnum.PROCESS_SUCCESS.getCode(),
                IndustryQualificationPo::getId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkReject(Long id) {
        logger.info("[行业资质信息审批驳回]---主键ID为{}", id);
        industryQualificationRepository.updateByOneParams(IndustryQualificationPo::getBusinessStatus, BusinessStatusEnum.PROCESS_REJECT.getCode(),
                IndustryQualificationPo::getId, id);
    }

    @Override
    public IPage<IndustryQualificationResponse> getPageInfo(IndustryQualificationRequest request) {
        logger.info("[查询行业资质分页信息]");
        Page<IndustryQualificationPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final Page<IndustryQualificationPo> poPage = industryQualificationRepository.getPageByParams(page, request);
        if (poPage.getTotal() <= 0) {
            return new Page<>(request.getPageIndex(), request.getPageSize());
        }
        final List<IndustryQualificationPo> records = poPage.getRecords();
        final List<IndustryQualificationResponse> responseList = IndustryQualificationCvt.INSTANCE.poToResponseBatch(records);

        // 重组分页信息
        Page<IndustryQualificationResponse> responsePage = new Page<>();
        responsePage.setRecords(responseList);
        responsePage.setSize(poPage.getSize());
        responsePage.setTotal(poPage.getTotal());
        responsePage.setCurrent(poPage.getCurrent());
        return responsePage;
    }


}
