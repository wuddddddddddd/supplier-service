package com.sole.saas.supplier.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.common.utils.RedisUtils;
import com.sole.saas.supplier.constant.*;
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

import java.util.*;
import java.util.stream.Collectors;

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

    private final ISupplierDictRepository supplierDictRepository;

    private final RedisUtils redisUtils;

    private final IDictInfoRepository dictInfoRepository;

    private final IBusinessHistoryRepository businessHistoryRepository;


    public SupplierServiceImpl(ISupplierBasicInfoRepository supplierBasicInfoRepository, IQualificationInfoRepository qualificationInfoRepository,
                               IRegisterInfoRepository registerInfoRepository, ISupplierUserInfoRepository supplierUserInfoRepository,
                               ISupplierBuyerUserRepository supplierBuyerUserRepository, ISupplierDictRepository supplierDictRepository,
                               RedisUtils redisUtils, IDictInfoRepository dictInfoRepository,
                               IBusinessHistoryRepository businessHistoryRepository) {
        this.supplierBasicInfoRepository = supplierBasicInfoRepository;
        this.qualificationInfoRepository = qualificationInfoRepository;
        this.registerInfoRepository = registerInfoRepository;
        this.supplierUserInfoRepository = supplierUserInfoRepository;
        this.supplierBuyerUserRepository = supplierBuyerUserRepository;
        this.supplierDictRepository = supplierDictRepository;
        this.redisUtils = redisUtils;
        this.dictInfoRepository = dictInfoRepository;
        this.businessHistoryRepository = businessHistoryRepository;
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
        // 账号: 8位数字 + 字母
        final String number = RandomUtil.randomNumbers(8);
        final String charNumber = RandomUtil.randomString(3);
        final String random = number + charNumber;
        supplierUserInfoPo.setAccount(random);
        supplierUserInfoPo.setPassword(random);
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
        basicInfoPo.setSelfSupportType(SelfSupportEnum.OTHER.getCode());
        basicInfoPo.setBusinessStatus(isDraft ? BusinessStatusEnum.DRAFT.getCode() : BusinessStatusEnum.IN_PROCESS.getCode());
        if (!isDraft && StrUtil.isBlank(basicInfoRequest.getCode())) {
            // 供应商编码: GYS+创建年月日+6位随机数字
            final long code = redisUtils.incr("SUPPLIER_CODE", 1);
            String format = String.format("%06d", code);
            String supplierCode = SupplierConstant.SUPPLIER_CODE_KEY + DateUtil.today() + format;
            basicInfoPo.setCode(supplierCode);
        }
        supplierBasicInfoRepository.updateById(basicInfoPo);

        // 主营行业信息
        final Long supplierId = basicInfoPo.getId();
        // 逻辑删除历史数据
        SupplierDictRequest supplierDictRequest = new SupplierDictRequest();
        supplierDictRequest.setSupplierId(supplierId);
        supplierDictRequest.setCode(SupplierDictCodeEnum.INDUSTRY.getCode());
        supplierDictRequest.setStatus(Constant.STATUS_NOT_DEL);
        supplierDictRepository.updateOneByParams(SupplierDictPo::getStatus, Constant.STATUS_DEL, supplierDictRequest);
        // 新增
        final List<Long> industryList = request.getIndustryList();
        List<SupplierDictPo> supplierDictPoList = new ArrayList<>();
        for (Long industryId : industryList) {
            SupplierDictPo supplierDictPo = new SupplierDictPo();
            supplierDictPo.setCode(SupplierDictCodeEnum.INDUSTRY.getCode());
            supplierDictPo.setSupplierId(supplierId);
            supplierDictPo.setDictId(industryId);
            supplierDictPoList.add(supplierDictPo);
        }
        supplierDictRepository.saveBatch(supplierDictPoList);

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
        logger.info("[获取供应商详细信息]---供应商ID为{}", supplierId);

        SupplierResponse response = new SupplierResponse();
        // 获取供应商基础信息
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                        .errorMessage(null, "根据供应商ID{}未获取到对应的供应商信息", supplierId);
        final SupplierBasicInfoResponse basicInfoResponse = SupplierBasicInfoCvt.INSTANCE.poToResponse(basicInfoPo);
        response.setBasicInfoResponse(basicInfoResponse);

        // 主营行业信息
        SupplierDictRequest supplierDictRequest = new SupplierDictRequest();
        supplierDictRequest.setSupplierId(supplierId);
        supplierDictRequest.setCode(SupplierDictCodeEnum.INDUSTRY.getCode());
        supplierDictRequest.setStatus(Constant.STATUS_NOT_DEL);
        final List<SupplierDictPo> supplierDictPoList = supplierDictRepository.getListByParams(supplierDictRequest);
        if (CollectionUtil.isNotEmpty(supplierDictPoList)) {
            final List<Long> industryIdList = supplierDictPoList.stream().map(SupplierDictPo::getDictId).collect(Collectors.toList());
            response.setIndustryList(industryIdList);
        }

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delSupplier(Long supplierId) {
        logger.info("[删除供应商]---供应商ID为{}", supplierId);
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getStatus, Constant.STATUS_DEL,
                SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    public IPage<SupplierPageResponse> getSupplierPageByParams(SupplierPageRequest request) {
        logger.info("[查询供应商分页信息]");
        Page<SupplierBasicInfoPo> page = new Page<>(request.getPageIndex(), request.getPageSize());
        final IPage<SupplierPageResponse> pageResponse = supplierBasicInfoRepository.getCustomerPage(page, request);
        if (pageResponse.getTotal() <= 0) {
            return pageResponse;
        }
        // 分页信息组装
        final List<SupplierPageResponse> list = pageResponse.getRecords();
        this.getSupplierPageInfo(list);
        return pageResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remarkSelfSupport(Long supplierId) {
        logger.info("[供应商标记自营]---供应商ID为{}", supplierId);
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getSelfSupportType,
                SelfSupportEnum.OWNER, SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelSelfSupport(Long supplierId) {
        logger.info("[供应商取消自营]---供应商ID为{}", supplierId);
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getSelfSupportType, SelfSupportEnum.OTHER.getCode(),
                SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopCooperation(Long supplierId, String reason) {
        logger.info("[供应商终止合作]----供应商ID为{}", supplierId);
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                .errorMessage(null, "根据供应商ID{}未查询到信息", supplierId);

        // 修改业务状态为终止合作状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                BusinessStatusEnum.STOP_COOPERATION.getCode(), SupplierBasicInfoPo::getId, supplierId);

        // 历史记录表记录业务状态变更
        // 根据供应商ID逻辑删除历史信息
        businessHistoryRepository.updateByOneParams(BusinessHistoryPo::getStatus, Constant.STATUS_DEL,
                BusinessHistoryPo::getBusinessId, supplierId);
        // 新增变更记录
        BusinessHistoryPo businessHistoryPo = new BusinessHistoryPo();
        businessHistoryPo.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryPo.setBusinessId(supplierId);
        businessHistoryPo.setBusinessStatus(BusinessStatusEnum.STOP_COOPERATION.getCode());
        businessHistoryPo.setOldBusinessStatus(basicInfoPo.getBusinessStatus());
        businessHistoryPo.setRemark(reason);
        businessHistoryRepository.save(businessHistoryPo);

        // 调用接口,所有商品下架
        /*final boolean isTrue = null == response || response.getError().getCode() != HttpStatus.OK.value();
        ExceptionUtils.error(isTrue)
                .errorMessage(null, "商品下架异常");*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recoverCooperation(Long supplierId) {
        logger.info("[供应商恢复合作]----供应商ID为{}", supplierId);

        // 获取历史业务记录表信息
        BusinessHistoryRequest businessHistoryRequest = new BusinessHistoryRequest();
        businessHistoryRequest.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryRequest.setBusinessId(supplierId);
        businessHistoryRequest.setStatus(Constant.STATUS_NOT_DEL);
        final BusinessHistoryPo historyPo = businessHistoryRepository.getByParams(businessHistoryRequest);
        ExceptionUtils.error(null == historyPo)
                .errorMessage(null, "根据变更类型{}供应商ID{}未查询到历史业务变更信息", HistoryTypeEnum.SUPPLIER.getCode(), supplierId);

        // 恢复终止合作前的业务状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                historyPo.getOldBusinessStatus(), SupplierBasicInfoPo::getId, supplierId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinBlack(Long supplierId, String reason) {
        logger.info("[供应商加黑]----供应商ID为{}", supplierId);
        final SupplierBasicInfoPo basicInfoPo = supplierBasicInfoRepository.getById(supplierId);
        ExceptionUtils.error(null == basicInfoPo)
                .errorMessage(null, "根据供应商ID{}未查询到信息", supplierId);

        // 修改业务状态为加黑状态
        supplierBasicInfoRepository.updateByOneParams(SupplierBasicInfoPo::getBusinessStatus,
                BusinessStatusEnum.BLACK.getCode(), SupplierBasicInfoPo::getId, supplierId);

        // 历史记录表记录业务状态变更
        // 根据供应商ID逻辑删除历史信息
        businessHistoryRepository.updateByOneParams(BusinessHistoryPo::getStatus, Constant.STATUS_DEL,
                BusinessHistoryPo::getBusinessId, supplierId);
        // 新增变更记录
        BusinessHistoryPo businessHistoryPo = new BusinessHistoryPo();
        businessHistoryPo.setType(HistoryTypeEnum.SUPPLIER.getCode());
        businessHistoryPo.setBusinessId(supplierId);
        businessHistoryPo.setBusinessStatus(BusinessStatusEnum.BLACK.getCode());
        businessHistoryPo.setOldBusinessStatus(basicInfoPo.getBusinessStatus());
        businessHistoryPo.setRemark(reason);
        businessHistoryRepository.save(businessHistoryPo);
    }

    private void getSupplierPageInfo(List<SupplierPageResponse> list) {
        // 经营类型ID集
        Set<Long> manageTypeIdSet = new HashSet<>();
        for (SupplierPageResponse response : list) {
            manageTypeIdSet.add(response.getManageTypeId());
        }
        // 经营类型<经营类型ID, 业务字典集>
        Map<Long, DictInfoPo> dictMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(manageTypeIdSet)) {
            DictInfoRequest dictInfoRequest = new DictInfoRequest();
            dictInfoRequest.setIdList(new ArrayList<>(manageTypeIdSet));
            final List<DictInfoPo> dictInfoPoList = dictInfoRepository.getListByParams(dictInfoRequest);
            if (CollectionUtil.isNotEmpty(dictInfoPoList)) {
                dictMap = dictInfoPoList.stream().collect(Collectors.toMap(DictInfoPo::getId, e -> e, (k1, k2) -> k1));
            }
        }

        // 组装数据
        for (SupplierPageResponse response : list) {
            final Long manageTypeId = response.getManageTypeId();
            if (dictMap.containsKey(manageTypeId)) {
                final DictInfoPo dictInfoPo = dictMap.get(manageTypeId);
                response.setManageTypeName(dictInfoPo.getName());
            }
        }
    }
}
