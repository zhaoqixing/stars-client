package com.stars.core;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 18:27
 */
public class RegisteServe {

    private String baseUrl;

    private String url;

    private RequestType requestType;

    public RegisteServe() {
    }

    public RegisteServe(String baseUrl, String url, RequestType requestType) {
        this.baseUrl = baseUrl;
        this.url = url;
        this.requestType = requestType;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public enum RequestType{
        GET,POST

        ;

        RequestType() {
        }

    }
}
