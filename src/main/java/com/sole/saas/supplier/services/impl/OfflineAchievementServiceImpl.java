package com.sole.saas.supplier.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.constant.OpinionTypeEnum;
import com.sole.saas.supplier.cvts.OfflineAchievementCvt;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.po.OfflineAchievementPo;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.models.response.OfflineAchievementResponse;
import com.sole.saas.supplier.repositorys.ICheckOpinionRepository;
import com.sole.saas.supplier.repositorys.IOfflineAchievementRepository;
import com.sole.saas.supplier.services.IOfflineAchievementService;
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
public class OfflineAchievementServiceImpl implements IOfflineAchievementService {
    private static final Logger logger = LoggerFactory.getLogger(OfflineAchievementServiceImpl.class);

    private final IOfflineAchievementRepository offlineAchievementRepository;

    private final ICheckOpinionRepository checkOpinionRepository;

    public OfflineAchievementServiceImpl(IOfflineAchievementRepository offlineAchievementRepository, ICheckOpinionRepository checkOpinionRepository) {
        this.offlineAchievementRepository = offlineAchievementRepository;
        this.checkOpinionRepository = checkOpinionRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOfflineAchievement(OfflineAchievementRequest request) {
        logger.info("[新增线下业绩信息]---供应商ID为{}", request.getSupplierId());
        // 实体转换
        final OfflineAchievementPo offlineAchievementPo = OfflineAchievementCvt.INSTANCE.requestToPo(request);
        // 业务状态为审批中
        offlineAchievementPo.setBusinessStatus(BusinessStatusEnum.IN_PROCESS.getCode());
        // 保存
        offlineAchievementRepository.save(offlineAchievementPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOfflineAchievement(OfflineAchievementRequest request) {
        logger.info("[修改线下业绩信息]---主键ID为{}", request.getId());
        // 实体转换
        final OfflineAchievementPo offlineAchievementPo = OfflineAchievementCvt.INSTANCE.requestToPo(request);
        // 业务状态为审批中
        offlineAchievementPo.setBusinessStatus(BusinessStatusEnum.IN_PROCESS.getCode());
        // 根据主键ID修改
        offlineAchievementRepository.updateById(offlineAchievementPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delOfflineAchievement(Long id) {
        logger.info("[删除线下业绩信息]---主键ID为{}", id);
        offlineAchievementRepository.updateByOneParams(OfflineAchievementPo::getStatus, Constant.STATUS_DEL,
                OfflineAchievementPo::getId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkApproval(Long id) {
        logger.info("[线下业绩信息审批通过]---主键ID为{}", id);
        offlineAchievementRepository.updateByOneParams(OfflineAchievementPo::getBusinessStatus, BusinessStatusEnum.PROCESS_SUCCESS,
                OfflineAchievementPo::getId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkReject(Long id, String reason, Long currentUserId) {
        logger.info("[线下业绩信息审批驳回]---主键ID为{}", id);
        // 修改业务状态
        offlineAchievementRepository.updateByOneParams(OfflineAchievementPo::getBusinessStatus, BusinessStatusEnum.PROCESS_REJECT,
                OfflineAchievementPo::getId, id);

        // 保存审批信息
        CheckOpinionPo checkOpinionPo = new CheckOpinionPo();
        checkOpinionPo.setType(OpinionTypeEnum.OFFLINE_ACHIEVEMENT.getCode());
        checkOpinionPo.setBusinessId(id);
        checkOpinionPo.setAssigneeId(currentUserId);
        checkOpinionPo.setOpinionType(BusinessStatusEnum.PROCESS_SUCCESS.getCode());
        checkOpinionRepository.save(checkOpinionPo);
    }

    @Override
    public IPage<OfflineAchievementResponse> getPageInfo(OfflineAchievementRequest request) {
        logger.info("[查询线下业绩分页信息]");
        Page<OfflineAchievementPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final Page<OfflineAchievementPo> poPage = offlineAchievementRepository.getPageByParams(page, request);
        if (poPage.getTotal() <= 0) {
            return new Page<>(request.getPageIndex(), request.getPageSize());
        }
        final List<OfflineAchievementPo> records = page.getRecords();
        final List<OfflineAchievementResponse> responseList = OfflineAchievementCvt.INSTANCE.poToResponseBatch(records);

        // 重组分页信息
        Page<OfflineAchievementResponse> responsePage = new Page<>();
        responsePage.setRecords(responseList);
        responsePage.setSize(poPage.getSize());
        responsePage.setTotal(poPage.getTotal());
        responsePage.setCurrent(poPage.getCurrent());
        return responsePage;
    }


}
