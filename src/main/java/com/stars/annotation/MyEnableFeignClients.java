package com.stars.annotation;

import com.stars.core.MyFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 16:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyFeignClientsRegistrar.class)
public @interface MyEnableFeignClients {

}
