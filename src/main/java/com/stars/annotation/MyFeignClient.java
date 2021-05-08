package com.stars.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyFeignClient {

    String baseUrl() default "";

    String serveName() default "";
}
