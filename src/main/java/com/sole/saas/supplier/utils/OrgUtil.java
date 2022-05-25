package com.sole.saas.supplier.utils;

import com.alibaba.fastjson.JSON;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.apis.OrgClient;
import com.sole.saas.supplier.models.response.CommonAreaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wjd
 * @description 获取区域信息.
 * @date 2022-05-25
 */
@Component
public class OrgUtil {
    private static final Logger logger = LoggerFactory.getLogger(OrgUtil.class);

    private final OrgClient orgClient;

    public OrgUtil(OrgClient orgClient) {
        this.orgClient = orgClient;
    }

    public List<CommonAreaResponse> getAreaByIdList(List<Long> idList) {
        logger.info("[根据区域ID集获取区域信息]");
        List<CommonAreaResponse> list = null;
        final Response response = orgClient.getAreaByIdList(idList);
        if (null != response) {
            ExceptionUtils.error(response.getError().getCode() != HttpStatus.OK.value())
                    .errorMessage(null, response.getError().getMessage());
            list = JSON.parseArray(JSON.toJSONString(response.getData()), CommonAreaResponse.class);
        }
        return list;
    }
}
