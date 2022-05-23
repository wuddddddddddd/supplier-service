package com.sole.saas.supplier.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wjd
 * @description 通过上下文获取用户/公司等信息(后期通过common修改完善,临时用)
 * @date 2022-05-12
 */
@Component
public class ContextUtil {

    private final HttpServletRequest request;

    public ContextUtil(HttpServletRequest request) {
        this.request = request;
    }

    public Long getCurrentCompanyId() {
        Long companyId = null;
        final String company = request.getHeader("companyId");
        if (StrUtil.isNotBlank(company)) {
            companyId = Long.valueOf(company);
        }
        return companyId;
    }

    public Long getCurrentUserId() {
        Long userId = null;
        final String user = request.getHeader("userId");
        if (StrUtil.isNotBlank(user)) {
            userId = Long.valueOf(user);
        }
        return userId;
    }

}
