package com.sole.saas.supplier.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.supplier.cvts.CheckOpinionCvt;
import com.sole.saas.supplier.models.po.CheckOpinionPo;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;
import com.sole.saas.supplier.repositorys.ICheckOpinionRepository;
import com.sole.saas.supplier.services.ICheckOpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wjd
 * @description 供应商审批记录信息服务层实现.
 * @date 2022-05-17
 */
@Service
public class CheckOpinionServiceImpl implements ICheckOpinionService {
    private static final Logger logger = LoggerFactory.getLogger(CheckOpinionServiceImpl.class);


    private final ICheckOpinionRepository checkOpinionRepository;


    public CheckOpinionServiceImpl(ICheckOpinionRepository checkOpinionRepository) {
        this.checkOpinionRepository = checkOpinionRepository;
    }


    @Override
    public List<CheckOpinionResponse> getOpinionListByParams(CheckOpinionRequest request) {
        logger.info("[根据条件查询审批信息集]");
        final List<CheckOpinionPo> checkOpinionPoList = checkOpinionRepository.getListByParams(request);
        return CheckOpinionCvt.INSTANCE.poToResponseBatch(checkOpinionPoList);
    }

    @Override
    public IPage<CheckOpinionResponse> getPageInfo(CheckOpinionRequest request) {
        logger.info("[查询审批意见分页信息]");
        Page<CheckOpinionPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final Page<CheckOpinionPo> poPage = checkOpinionRepository.getPageByParams(page, request);
        if (poPage.getTotal() <= 0) {
            return new Page<>(request.getPageIndex(), request.getPageSize());
        }
        final List<CheckOpinionPo> records = page.getRecords();
        final List<CheckOpinionResponse> responseList = CheckOpinionCvt.INSTANCE.poToResponseBatch(records);

        // 重组分页信息
        Page<CheckOpinionResponse> responsePage = new Page<>();
        responsePage.setRecords(responseList);
        responsePage.setSize(poPage.getSize());
        responsePage.setTotal(poPage.getTotal());
        responsePage.setCurrent(poPage.getCurrent());
        return responsePage;
    }
}
