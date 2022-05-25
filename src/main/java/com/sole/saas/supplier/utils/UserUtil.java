package com.sole.saas.supplier.utils;

import com.alibaba.fastjson.JSON;
import com.sole.saas.common.apis.UserClient;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.models.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wjd
 * @description 获取用户信息.
 * @date 2022-05-25
 */
@Component
public class UserUtil {
    private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);

    private final UserClient userClient;

    public UserUtil(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UserResponse> getUserListByIdList(List<Integer> idList) {
        logger.info("[根据用户ID集获取用户信息]");
        List<UserResponse> list = null;
        final Response response = userClient.getUserListByIdList(idList);
        if (null != response) {
            ExceptionUtils.error(response.getError().getCode() != HttpStatus.OK.value())
                    .errorMessage(null, response.getError().getMessage());
            list = JSON.parseArray(JSON.toJSONString(response.getData()), UserResponse.class);
        }
        return list;
    }

}
