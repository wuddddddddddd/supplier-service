package com.sole.saas.supplier.apis;

import com.sole.saas.common.models.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-25
 */
@FeignClient(value = "organization-service")
public interface OrgClient {

    @ApiOperation(value = "根据主键ID集获取区域信息集")
    @PostMapping("/external/getAreaByIdList")
    Response getAreaByIdList(@RequestBody List<Long> idList);
}
