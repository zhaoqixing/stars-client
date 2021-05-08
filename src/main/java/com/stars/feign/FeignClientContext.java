package com.stars.feign;

import com.stars.core.RegisteServe;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/29 16:16
 */
public class FeignClientContext{

    protected Log logger = LogFactory.getLog(this.getClass());

    protected RegisteServe.RequestType requestType;

    protected String url;

    protected String mediaType;

    public FeignClientContext() {
    }

    public FeignClientContext(RegisteServe.RequestType requestType, String url, String mediaType) {
        this.requestType = requestType;
        this.url = url;
        this.mediaType = mediaType;
    }

}
