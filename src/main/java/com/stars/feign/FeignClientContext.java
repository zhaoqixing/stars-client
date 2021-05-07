package com.stars.feign;

import com.stars.core.RegisteServe;

import java.util.HashMap;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/29 16:16
 */
public class FeignClientContext{

    RegisteServe.RequestType requestType;

    String url;

    String mediaType;

    ParamBean paramBean;

    public FeignClientContext() {
    }

    public FeignClientContext(RegisteServe.RequestType requestType, String url, String mediaType) {
        this.requestType = requestType;
        this.url = url;
        this.mediaType = mediaType;
    }

}
