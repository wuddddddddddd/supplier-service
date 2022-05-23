package com.sole.saas.supplier.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.ComplaintInfoCvt;
import com.sole.saas.supplier.models.po.ComplaintInfoPo;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.models.response.ComplaintInfoResponse;
import com.sole.saas.supplier.repositorys.IComplaintInfoRepository;
import com.sole.saas.supplier.services.IComplaintInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Service
public class ComplaintInfoServiceImpl implements IComplaintInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ComplaintInfoServiceImpl.class);

    private final IComplaintInfoRepository complaintInfoRepository;

    public ComplaintInfoServiceImpl(IComplaintInfoRepository complaintInfoRepository) {
        this.complaintInfoRepository = complaintInfoRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOfflineAchievement(ComplaintInfoRequest request) {
        logger.info("[新增投诉信息]---供应商ID为{}", request.getSupplierId());
        // 实体转换
        final ComplaintInfoPo complaintInfoPo = ComplaintInfoCvt.INSTANCE.requestToPo(request);
        // 业务状态为未处理
        complaintInfoPo.setBusinessStatus(BusinessStatusEnum.NOT_DEAL.getCode());
        // 保存
        complaintInfoRepository.save(complaintInfoPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDealDone(Long id, String remark) {
        logger.info("[投诉信息改为已处理]---主键ID为{}", id);
        // 修改业务状态为已处理
        ComplaintInfoPo complaintInfoPo = new ComplaintInfoPo();
        complaintInfoPo.setId(id);
        complaintInfoPo.setBusinessStatus(BusinessStatusEnum.DEAL_DONE.getCode());
        complaintInfoPo.setDealRemark(remark);
        // 修改
        complaintInfoRepository.updateById(complaintInfoPo);
    }

    @Override
    public IPage<ComplaintInfoResponse> getPageInfo(ComplaintInfoRequest request) {
        logger.info("[查询投诉信息分页信息]");
        Page<ComplaintInfoPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final Page<ComplaintInfoPo> poPage = complaintInfoRepository.getPageByParams(page, request);
        if (poPage.getTotal() <= 0) {
            return new Page<>(request.getPageIndex(), request.getPageSize());
        }
        final List<ComplaintInfoPo> records = page.getRecords();
        final List<ComplaintInfoResponse> responseList = ComplaintInfoCvt.INSTANCE.poToResponseBatch(records);

        // 重组分页信息
        Page<ComplaintInfoResponse> responsePage = new Page<>();
        responsePage.setRecords(responseList);
        responsePage.setSize(poPage.getSize());
        responsePage.setTotal(poPage.getTotal());
        responsePage.setCurrent(poPage.getCurrent());
        return responsePage;
    }
}
