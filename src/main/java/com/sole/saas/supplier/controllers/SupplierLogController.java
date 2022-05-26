package com.sole.saas.supplier.controllers;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.models.response.UserResponse;
import com.sole.saas.common.utils.ContextUtil;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;
import com.sole.saas.supplier.services.ISupplierLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Api(value="供应商记录信息控制层")
@RestController
@RequestMapping("/supplierLog")
public class SupplierLogController {

    private final ISupplierLogService supplierLogService;


    public SupplierLogController(ISupplierLogService supplierLogService) {
        this.supplierLogService = supplierLogService;
    }

    @ApiOperation(value = "初始化创建简要供应商")
    @PostMapping("/initCreateSupplier")
    public Response initCreateSupplier(@RequestBody InitSupplierRequest request) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        request.setBuyerUserId(Long.valueOf(currentUser.getId()));
        supplierLogService.initCreateSupplier(request);
        return new Response<>();
    }

    @ApiOperation(value = "完善供应商信息")
    @PostMapping("/addSupplier")
    public Response addSupplier(@RequestBody SupplierRequest request) {
        supplierLogService.addSupplier(request, false);
        return new Response<>();
    }

    @ApiOperation(value = "保存草稿")
    @PostMapping("/addSupplierDraft")
    public Response addSupplierDraft(@RequestBody SupplierRequest request) {
        supplierLogService.addSupplier(request, true);
        return new Response<>();
    }

    @ApiOperation(value = "删除供应商")
    @GetMapping("/delSupplier")
    public Response delSupplier(@RequestParam Long supplierId) {
        supplierLogService.delSupplier(supplierId);
        return new Response<>();
    }

    @ApiOperation(value = "获取供应商记录信息")
    @GetMapping("/getSupplierLogBySupplierId")
    public Response getSupplierLogBySupplierId(@RequestParam Long supplierId) {
        final SupplierResponse info = supplierLogService.getSupplierLogBySupplierId(supplierId);
        return new Response<>(info);
    }

    @ApiOperation(value = "查询供应商记录分页信息")
    @GetMapping("/getSupplierLogPageByParams")
    public Response getSupplierLogPageByParams(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,
                                               @RequestParam(required = false) String businessStatusLists,
                                               @RequestParam(required = false) Integer businessStatus,
                                               @RequestParam(required = false) String supplierName,
                                               @RequestParam(required = false) Integer source,
                                               @RequestParam(required = false) String userName,
                                               @RequestParam(required = false) String userTelephone) {
        SupplierPageRequest request = new SupplierPageRequest();
        if (StrUtil.isNotBlank(businessStatusLists)) {
            final List<Integer> businessStatusList = Convert.toList(Integer.class, businessStatusLists);
            request.setBusinessStatusList(businessStatusList);
        }
        request.setBusinessStatus(businessStatus);
        request.setSupplierName(supplierName);
        request.setSource(source);
        request.setUserName(userName);
        request.setUserTelephone(userTelephone);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<SupplierPageResponse> page = supplierLogService.getSupplierLogPageByParams(request);
        return new Response<>(page);
    }

    @ApiOperation(value = "供应商审批通过")
    @GetMapping("/checkApproval")
    public Response checkApproval(@RequestParam Long supplierId) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        supplierLogService.checkApproval(supplierId, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }

    @ApiOperation(value = "供应商审批驳回")
    @GetMapping("/checkReject")
    public Response checkReject(@RequestParam Long supplierId, @RequestParam String reason) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        supplierLogService.checkReject(supplierId, reason, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }



}
