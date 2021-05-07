package com.stars.core;

import com.stars.annotation.Get;
import com.stars.annotation.Post;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 18:27
 */
public class RegisteServe {

    protected String baseUrl;

    protected String url;

    protected RequestType requestType;

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

    public static enum RequestType{
        GET(Get.class),POST(Post.class)

        ;
        Class<?> aClass;

        RequestType(Class<?> aClass) {
            this.aClass = aClass;
        }

        public Class<?> getaClass() {
            return aClass;
        }

    }
}
