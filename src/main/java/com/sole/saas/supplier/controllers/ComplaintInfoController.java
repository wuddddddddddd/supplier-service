package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.models.request.ComplaintInfoRequest;
import com.sole.saas.supplier.models.response.ComplaintInfoResponse;
import com.sole.saas.supplier.services.IComplaintInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Api(value="投诉信息控制层")
@RestController
@RequestMapping("/complaintInfo")
public class ComplaintInfoController {

    private final IComplaintInfoService complaintInfoService;

    public ComplaintInfoController(IComplaintInfoService complaintInfoService) {
        this.complaintInfoService = complaintInfoService;
    }

    @ApiOperation(value = "新增投诉信息")
    @PostMapping("/addOfflineAchievement")
    public Response addOfflineAchievement(@RequestBody ComplaintInfoRequest request) {
        complaintInfoService.addOfflineAchievement(request);
        return new Response<>();
    }

    @ApiOperation(value = "投诉信息已处理")
    @PostMapping("/updateDealDone")
    public Response updateDealDone(@RequestParam Long id, @RequestParam String remark) {
        complaintInfoService.updateDealDone(id, remark);
        return new Response<>();
    }

    @ApiOperation(value = "查询供应商记录分页信息")
    @GetMapping("/getPageInfo")
    public Response getPageInfo(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        ComplaintInfoRequest request = new ComplaintInfoRequest();
        request.setPageSize(pageSize);
        request.setPageIndex(pageIndex);
        final IPage<ComplaintInfoResponse> pageInfo = complaintInfoService.getPageInfo(request);
        return new Response<>(pageInfo);
    }


}
