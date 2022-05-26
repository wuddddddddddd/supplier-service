package com.sole.saas.supplier.controllers;

import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.services.ISupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjd
 * @description 外部服务调用.
 * @date 2022-05-26
 */
@Api(value="外部服务调用")
@RestController
@RequestMapping("/external")
public class ExternalController {

    private final ISupplierInfoService supplierInfoService;

    public ExternalController(ISupplierInfoService supplierInfoService) {
        this.supplierInfoService = supplierInfoService;
    }

    @ApiOperation(value = "修改供应商状态为合格")
    @GetMapping("/supplierStandard")
    public Response supplierStandard(@RequestParam Long supplierId) {
        supplierInfoService.updateBusinessById(supplierId, BusinessStatusEnum.STANDARD.getCode());
        return new Response<>();
    }
}
