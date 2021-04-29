package com.stars.feign;

import java.lang.annotation.Annotation;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/29 16:25
 */
public class ParamBean {

    private String key;

    private Object value;

    private Annotation annotation;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public ParamBean(String key, Object value, Annotation annotation) {
        this.key = key;
        this.value = value;
        this.annotation = annotation;
    }
}
