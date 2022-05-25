package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.models.response.UserResponse;
import com.sole.saas.common.utils.ContextUtil;
import com.sole.saas.supplier.models.request.OfflineAchievementRequest;
import com.sole.saas.supplier.models.response.OfflineAchievementResponse;
import com.sole.saas.supplier.services.IOfflineAchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjd
 * @description 线下业绩信息信息控制层.
 * @date 2022-05-23
 */
@Api(value="线下业绩信息信息控制层")
@RestController
@RequestMapping("/industryQualification")
public class OfflineAchievementController {

    private final IOfflineAchievementService offlineAchievementService;


    public OfflineAchievementController(IOfflineAchievementService offlineAchievementService) {
        this.offlineAchievementService = offlineAchievementService;
    }

    @ApiOperation(value = "新增行业资质信息")
    @PostMapping("/addIndustryQualification")
    public Response addIndustryQualification(@RequestBody OfflineAchievementRequest request) {
        offlineAchievementService.addOfflineAchievement(request);
        return new Response<>();
    }

    @ApiOperation(value = "修改线下业绩信息")
    @PostMapping("/updateOfflineAchievement")
    public Response updateOfflineAchievement(@RequestBody OfflineAchievementRequest request) {
        offlineAchievementService.updateOfflineAchievement(request);
        return new Response<>();
    }

    @ApiOperation(value = "删除线下业绩信息")
    @GetMapping("/delOfflineAchievement")
    public Response delOfflineAchievement(@RequestParam Long id) {
        offlineAchievementService.delOfflineAchievement(id);
        return new Response<>();
    }

    @ApiOperation(value = "线下业绩信息审批通过")
    @GetMapping("/checkApproval")
    public Response checkApproval(@RequestParam Long id) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        offlineAchievementService.checkApproval(id, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }

    @ApiOperation(value = "线下业绩信息审批驳回")
    @GetMapping("/checkReject")
    public Response checkReject(@RequestParam Long id, @RequestParam String reason) {
        final UserResponse currentUser = ContextUtil.getCurrentUser();
        offlineAchievementService.checkReject(id, reason, Long.valueOf(currentUser.getId()));
        return new Response<>();
    }

    @ApiOperation(value = "线下业绩分页信息")
    @GetMapping("/getPageInfo")
    public Response getPageInfo(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        OfflineAchievementRequest request = new OfflineAchievementRequest();
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<OfflineAchievementResponse> pageInfo = offlineAchievementService.getPageInfo(request);
        return new Response<>(pageInfo);
    }
}
