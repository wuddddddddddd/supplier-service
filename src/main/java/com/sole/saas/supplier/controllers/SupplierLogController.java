package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.models.request.InitSupplierRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.request.SupplierRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;
import com.sole.saas.supplier.services.ISupplierLogService;
import com.sole.saas.supplier.utils.ContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    private final ContextUtil contextUtil;

    public SupplierLogController(ISupplierLogService supplierLogService, ContextUtil contextUtil) {
        this.supplierLogService = supplierLogService;
        this.contextUtil = contextUtil;
    }

    @ApiOperation(value = "初始化创建简要供应商")
    @PostMapping("/initCreateSupplier")
    public Response initCreateSupplier(@RequestBody InitSupplierRequest request) {
        final Long currentUserId = contextUtil.getCurrentUserId();
        request.setBuyerUserId(currentUserId);
        supplierLogService.initCreateSupplier(request);
        return new Response<>();
    }

    @ApiOperation(value = "完善供应商信息")
    @PostMapping("/addSupplier")
    public Response addSupplier(@RequestBody SupplierRequest request) {
        final Long currentUserId = contextUtil.getCurrentUserId();
        request.setSupplierId(currentUserId);
        supplierLogService.addSupplier(request, false);
        return new Response<>();
    }

    @ApiOperation(value = "完善供应商信息")
    @PostMapping("/addSupplierDraft")
    public Response addSupplierDraft(@RequestBody SupplierRequest request) {
        final Long currentUserId = contextUtil.getCurrentUserId();
        request.setSupplierId(currentUserId);
        supplierLogService.addSupplier(request, true);
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
    public Response getSupplierLogPageByParams(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        SupplierPageRequest request = new SupplierPageRequest();
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<SupplierPageResponse> page = supplierLogService.getSupplierLogPageByParams(request);
        return new Response<>(page);
    }

    @ApiOperation(value = "供应商审批通过")
    @GetMapping("/checkApproval")
    public Response checkApproval(@RequestParam Long supplierId) {
        final Long currentUserId = contextUtil.getCurrentUserId();
        supplierLogService.checkApproval(supplierId, currentUserId);
        return new Response<>();
    }

    @ApiOperation(value = "供应商审批驳回")
    @GetMapping("/checkReject")
    public Response checkReject(@RequestParam Long supplierId, @RequestParam String reason) {
        final Long currentUserId = contextUtil.getCurrentUserId();
        supplierLogService.checkReject(supplierId, reason, currentUserId);
        return new Response<>();
    }



}
