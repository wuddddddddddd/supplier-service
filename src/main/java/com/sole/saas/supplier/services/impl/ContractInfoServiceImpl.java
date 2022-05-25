package com.sole.saas.supplier.services.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.cvts.ContractInfoCvt;
import com.sole.saas.supplier.models.po.ContractInfoPo;
import com.sole.saas.supplier.models.request.ContractInfoRequest;
import com.sole.saas.supplier.models.response.ContractInfoResponse;
import com.sole.saas.supplier.repositorys.IContractInfoRepository;
import com.sole.saas.supplier.services.IContractInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wjd
 * @description 供应商合同信息服务层实现.
 * @date 2022-05-23
 */
@Service
public class ContractInfoServiceImpl implements IContractInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ContractInfoServiceImpl.class);

    private final IContractInfoRepository contractInfoRepository;


    public ContractInfoServiceImpl(IContractInfoRepository contractInfoRepository) {
        this.contractInfoRepository = contractInfoRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOfflineAchievement(ContractInfoRequest request) {
        logger.info("[新增合同信息]---供应商ID为{}", request.getSupplierId());
        // 实体转换
        final ContractInfoPo contractInfoPo = ContractInfoCvt.INSTANCE.requestToPo(request);

        //业务状态
        final Integer businessStatus = this.getBusinessStatus(request.getStartDate(), request.getEndData());
        contractInfoPo.setBusinessStatus(businessStatus);

        // 保存
        contractInfoRepository.save(contractInfoPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBusinessStatus(Long id) {
        logger.info("[修改合同业务状态信息]---供应商ID为{}", id);
        final ContractInfoPo contractInfoPo = contractInfoRepository.getById(id);
        ExceptionUtils.error(null == contractInfoPo)
                .errorMessage(null, "[修改合同业务状态信息]---根据供应商ID{}未查询到合同信息", id);
        //业务状态
        final Integer businessStatus = this.getBusinessStatus(contractInfoPo.getStartDate(), contractInfoPo.getEndData());

        // 修改
        contractInfoRepository.updateByOneParams(ContractInfoPo::getBusinessStatus, businessStatus,
                ContractInfoPo::getId, id);
    }

    @Override
    public IPage<ContractInfoResponse> getPageInfo(ContractInfoRequest request) {
        logger.info("[查询合同信息分页信息]");
        Page<ContractInfoPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final Page<ContractInfoPo> poPage = contractInfoRepository.getPageByParams(page, request);
        if (poPage.getTotal() <= 0) {
            return new Page<>(request.getPageIndex(), request.getPageSize());
        }
        final List<ContractInfoPo> records = page.getRecords();
        final List<ContractInfoResponse> responseList = ContractInfoCvt.INSTANCE.poToResponseBatch(records);

        // 重组分页信息
        Page<ContractInfoResponse> responsePage = new Page<>();
        responsePage.setRecords(responseList);
        responsePage.setSize(poPage.getSize());
        responsePage.setTotal(poPage.getTotal());
        responsePage.setCurrent(poPage.getCurrent());
        return responsePage;
    }

    /**
      * @description 根据合同起始日期&截至日期判断当前合同状态.
      * @author wjd
      * @date 2022/5/23
      * @param startDate 开始日期
      * @param endData 截至日期
      * @return 合同状态
      */
    private Integer getBusinessStatus(Date startDate, Date endData) {
        Integer businessStatus = null;
        // 业务状态: 同当前日期判断比较
        final DateTime now = DateUtil.date();
        final int compareStartDate = DateUtil.compare(startDate, now, DatePattern.PURE_DATE_PATTERN);
        final int compareEndDate = DateUtil.compare(endData, now, DatePattern.PURE_DATE_PATTERN);

        // 当前日期小于起始日期表示还未生效
        if (compareStartDate < 0) {
            businessStatus = BusinessStatusEnum.NOT_ACTIVE.getCode();
        }
        // 当前日期大于等于起始日期小于截至日期已生效
        if (compareStartDate >= 0 && compareEndDate <= 0) {
            businessStatus = BusinessStatusEnum.IN_ACTIVE.getCode();
        }
        // 大于截至日期已过期
        if (compareEndDate > 0) {
            businessStatus = BusinessStatusEnum.EXPIRE.getCode();
        }
        return businessStatus;
    }

}
