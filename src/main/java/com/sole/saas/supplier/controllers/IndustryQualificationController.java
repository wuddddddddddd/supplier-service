package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.models.response.UserResponse;
import com.sole.saas.common.utils.ContextUtil;
import com.sole.saas.supplier.models.request.IndustryQualificationRequest;
import com.sole.saas.supplier.models.response.IndustryQualificationResponse;
import com.sole.saas.supplier.services.IIndustryQualificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Api(value="行业资质信息信息控制层")
@RestController
@RequestMapping("/industryQualification")
public class IndustryQualificationController {

    private final IIndustryQualificationService iIndustryQualificationService;


    public IndustryQualificationController(IIndustryQualificationService iIndustryQualificationService) {
        this.iIndustryQualificationService = iIndustryQualificationService;
    }

    @ApiOperation(value = "新增行业资质信息")
    @PostMapping("/addIndustryQualification")
    public Response addIndustryQualification(@RequestBody IndustryQualificationRequest request) {
        iIndustryQualificationService.addIndustryQualification(request);
        return new Response<>();
    }

    @ApiOperation(value = "修改行业资质信息")
    @PostMapping("/updateIndustryQualification")
    public Response updateIndustryQualification(@RequestBody IndustryQualificationRequest request) {
        iIndustryQualificationService.updateIndustryQualification(request);
        return new Response<>();
    }

    @ApiOperation(value = "删除行业资质信息")
    @GetMapping("/delIndustryQualification")
    public Response delIndustryQualification(@RequestParam Long id) {
        iIndustryQualificationService.delIndustryQualification(id);
        return new Response<>();
    }

    @ApiOperation(value = "行业资质信息审批通过")
    @GetMapping("/checkApproval")
    public Response checkApproval(@RequestParam Long id) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        iIndustryQualificationService.checkApproval(id, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }

    @ApiOperation(value = "行业资质信息审批驳回")
    @GetMapping("/checkReject")
    public Response checkReject(@RequestParam Long id, @RequestParam(required = false) String reason) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        iIndustryQualificationService.checkReject(id, reason, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }

    @ApiOperation(value = "行业资质分页信息")
    @GetMapping("/getPageInfo")
    public Response getPageInfo(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        IndustryQualificationRequest request = new IndustryQualificationRequest();
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<IndustryQualificationResponse> pageInfo = iIndustryQualificationService.getPageInfo(request);
        return new Response<>(pageInfo);
    }



}
