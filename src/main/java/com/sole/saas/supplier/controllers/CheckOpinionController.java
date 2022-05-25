package com.sole.saas.supplier.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sole.saas.common.models.Response;
import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;
import com.sole.saas.supplier.services.ICheckOpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Api(value="审批意见信息控制层")
@RestController
@RequestMapping("/checkOpinion")
public class CheckOpinionController {

    private final ICheckOpinionService checkOpinionService;

    public CheckOpinionController(ICheckOpinionService checkOpinionService) {
        this.checkOpinionService = checkOpinionService;
    }

    @ApiOperation(value = "根据自定义条件查询结果集")
    @GetMapping("/getOpinionList")
    public Response getOpinionList(@RequestParam(required = false) Integer type,
                                   @RequestParam(required = false) Long businessId) {
        CheckOpinionRequest request = new CheckOpinionRequest();
        request.setType(type);
        request.setBusinessId(businessId);
        final List<CheckOpinionResponse> list = checkOpinionService.getOpinionListByParams(request);
        return new Response<>(list);
    }

    @ApiOperation(value = "根据自定义条件查询结果集带分页")
    @GetMapping("/getPageInfo")
    public Response getPageInfo(@RequestParam(required = false) Integer type,
                                   @RequestParam(required = false) Long businessId,
                                   @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        CheckOpinionRequest request = new CheckOpinionRequest();
        request.setType(type);
        request.setBusinessId(businessId);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        final IPage<CheckOpinionResponse> page = checkOpinionService.getPageInfo(request);
        return new Response<>(page);
    }

}
