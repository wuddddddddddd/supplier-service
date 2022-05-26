package com.sole.saas.supplier.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.sole.saas.common.models.Response;
import com.sole.saas.common.utils.ExceptionUtils;
import com.sole.saas.supplier.apis.OrgClient;
import com.sole.saas.supplier.models.response.CommonAreaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
      * @description 根据区域ID查询结果集并转换成Map.
      * @author wjd
      * @date 2022/5/26
      * @param idList 待查询的区域ID集
      * @return 区域结果集并转换成Map
      */
    public Map<Long, CommonAreaResponse> getAreaMapByIdList(List<Long> idList) {
        Map<Long, CommonAreaResponse> map = new HashMap<>();
        final List<CommonAreaResponse> areaList = this.getAreaByIdList(idList);
        if (CollectionUtil.isEmpty(areaList)) {
            return map;
        }
        map = areaList.stream().collect(Collectors.toMap(CommonAreaResponse::getAreaId, e -> e, (k1, k2) -> k1));
        return map;
    }
}
