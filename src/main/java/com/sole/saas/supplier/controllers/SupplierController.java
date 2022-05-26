package com.sole.saas.supplier.controllers;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.models.request.SupplierBuyerUserRequest;
import com.sole.saas.supplier.models.request.SupplierPageRequest;
import com.sole.saas.supplier.models.response.SupplierPageResponse;
import com.sole.saas.supplier.models.response.SupplierResponse;
import com.sole.saas.supplier.services.ISupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description: 供应商信息控制层.
 * @Author wjd
 * @date 2022/5/10
 */
@Api(value="供应商信息控制层")
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final ISupplierInfoService supplierInfoService;

    public SupplierController(ISupplierInfoService supplierInfoService) {
        this.supplierInfoService = supplierInfoService;
    }

    @ApiOperation(value = "获取供应商详细信息")
    @GetMapping("/getSupplierInfoById")
    public Response getSupplierInfoById(Long supplierId) {
        final SupplierResponse info = supplierInfoService.getSupplierInfoById(supplierId);
        return new Response<>(info);
    }

    @ApiOperation(value = "查询供应商分页信息")
    @GetMapping("/getSupplierPageByParams")
    public Response getSupplierPageByParams(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,
                                            @RequestParam(required = false) String businessStatusLists,
                                            @RequestParam(required = false) Integer businessStatus,
                                            @RequestParam(required = false) String supplierName,
                                            @RequestParam(required = false) Integer source,
                                            @RequestParam(required = false) Long buyerUserId,
                                            @RequestParam(required = false) Date registeredDate,
                                            @RequestParam(required = false) Integer selfSupportType) {
        SupplierPageRequest request = new SupplierPageRequest();
        if (StrUtil.isNotBlank(businessStatusLists)) {
            final List<Integer> businessStatusList = Convert.toList(Integer.class, businessStatusLists);
            request.setBusinessStatusList(businessStatusList);
        }
        request.setBusinessStatus(businessStatus);
        request.setSupplierName(supplierName);
        request.setSource(source);
        request.setBuyerUserId(buyerUserId);
        request.setRegisteredDate(registeredDate);
        request.setSelfSupportType(selfSupportType);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<SupplierPageResponse> page = supplierInfoService.getSupplierPageByParams(request);
        return new Response<>(page);
    }

    @ApiOperation(value = "供应商标记自营")
    @GetMapping("/remarkSelfSupport")
    public Response remarkSelfSupport(@RequestParam Long supplierId) {
        supplierInfoService.remarkSelfSupport(supplierId);
        return new Response<>();
    }

    @ApiOperation(value = "供应商取消自营")
    @GetMapping("/cancelSelfSupport")
    public Response cancelSelfSupport(@RequestParam Long supplierId) {
        supplierInfoService.cancelSelfSupport(supplierId);
        return new Response<>();
    }

    @ApiOperation(value = "供应商终止合作")
    @GetMapping("/stopCooperation")
    public Response stopCooperation(@RequestParam Long supplierId, @RequestParam(required = false) String reason) {
        supplierInfoService.stopCooperation(supplierId, reason);
        return new Response<>();
    }

    @ApiOperation(value = "供应商恢复合作")
    @GetMapping("/recoverCooperation")
    public Response recoverCooperation(@RequestParam Long supplierId) {
        supplierInfoService.recoverCooperation(supplierId);
        return new Response<>();
    }

    @ApiOperation(value = "供应商加黑")
    @GetMapping("/joinBlack")
    public Response joinBlack(@RequestParam Long supplierId, @RequestParam(required = false) String reason) {
        supplierInfoService.joinBlack(supplierId, reason);
        return new Response<>();
    }

    @ApiOperation(value = "分配供应商")
    @PostMapping("/updateBuyerUser")
    public Response updateBuyerUser(@RequestBody SupplierBuyerUserRequest request) {
        supplierInfoService.updateBuyerUser(request);
        return new Response<>();
    }


}
