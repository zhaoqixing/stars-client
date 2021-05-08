package com.stars.core;

import com.stars.feign.MyClientInvocationHandler;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:16
 */
public class MyFactoryBean implements FactoryBean {

    private String baseUrl;

    private String serveName;

    private Class<?> type;

    public MyFactoryBean(String baseUrl, Class<?> type) {
        this.baseUrl = baseUrl;
        this.type = type;
    }

    public MyFactoryBean(Class<?> type, String serveName){
        this.serveName = serveName;
        this.type = type;
    }

    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {type}, new MyClientInvocationHandler(baseUrl, serveName));
    }

    public Class<?> getObjectType() {
        return type;
    }

    public boolean isSingleton() {
        return false;
    }
}
