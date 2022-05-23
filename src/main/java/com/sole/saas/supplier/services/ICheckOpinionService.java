package com.sole.saas.supplier.services;

import com.sole.saas.supplier.models.request.CheckOpinionRequest;
import com.sole.saas.supplier.models.response.CheckOpinionResponse;

import java.util.List;

/**
 * @author wjd
 * @description
 * @date 2022-05-17
 */
public interface ICheckOpinionService {

    List<CheckOpinionResponse> getOpinionListByParams(CheckOpinionRequest request);
}
