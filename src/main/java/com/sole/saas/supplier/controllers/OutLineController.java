package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.constant.Constant;
import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.constant.BusinessStatusEnum;
import com.sole.saas.supplier.models.request.OutlinePageRequest;
import com.sole.saas.supplier.models.request.OutlineRequest;
import com.sole.saas.supplier.models.response.OutLinePageResponse;
import com.sole.saas.supplier.models.response.OutlineResponse;
import com.sole.saas.supplier.services.IOutlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-26
 */
@Api(value="供应商违规信息信息控制层")
@RestController
@RequestMapping("/outLine")
public class OutLineController {

    private final IOutlineService outlineService;

    public OutLineController(IOutlineService outlineService) {
        this.outlineService = outlineService;
    }

    @ApiOperation(value = "新增供应商违规信息")
    @PostMapping("/addOutLine")
    public Response addOutLine(@RequestBody OutlineRequest request) {
        outlineService.addOutLine(request);
        return new Response<>();
    }

    @ApiOperation(value = "标记整改")
    @PostMapping("/remarkCorrect")
    public Response remarkCorrect(@RequestParam Long id) {
        outlineService.updateBusinessStatus(id, BusinessStatusEnum.CORRECT.getCode());
        return new Response<>();
    }

    @ApiOperation(value = "查询供应商违规分页信息")
    @GetMapping("/getCustomerPage")
    public Response getCustomerPage(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,
                                    @RequestParam(required = false) String supplierName,
                                    @RequestParam(required = false) Long buyerUserId,
                                    @RequestParam(required = false) Long provinceId,
                                    @RequestParam(required = false) Long cityId,
                                    @RequestParam(required = false) Long districtId) {
        OutlinePageRequest request = new OutlinePageRequest();
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        request.setSupplierName(supplierName);
        request.setBuyerUserId(buyerUserId);
        request.setProvinceId(provinceId);
        request.setCityId(cityId);
        request.setDistrictId(districtId);
        final IPage<OutLinePageResponse> page = outlineService.getCustomerPage(request);
        return new Response<>(page);
    }

    @ApiOperation(value = "根据供应商ID查询违规信息集")
    @GetMapping("/getListBySupplierId")
    public Response getListBySupplierId(@RequestParam Long supplierId) {
        OutlineRequest request = new OutlineRequest();
        request.setSupplierId(supplierId);
        request.setStatus(Constant.STATUS_NOT_DEL);
        final List<OutlineResponse> list = outlineService.getListByParams(request);
        return new Response<>(list);
    }

}
