package com.sole.saas.supplier.controllers;

import com.sole.saas.supplier.services.ICheckOpinionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjd
 * @description
 * @date 2022-05-23
 */
@Api(value="审批意见信息控制层")
@RestController
@RequestMapping("/supplier")
public class CheckOpinionController {

    private final ICheckOpinionService checkOpinionService;

    public CheckOpinionController(ICheckOpinionService checkOpinionService) {
        this.checkOpinionService = checkOpinionService;
    }



}
