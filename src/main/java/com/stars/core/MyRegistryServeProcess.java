package com.stars.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 后置处理器内发起注册
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 18:21
 */
public class MyRegistryServeProcess implements BeanDefinitionRegistryPostProcessor {

    protected List<RegisteServe> registeServeBean = new ArrayList<RegisteServe>();

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        beanDefinitionRegistry.getBeanDefinitionNames();
        System.out.println("进入到bean后置处理器：todo : 准备注册该服务地址...(未完成)");

    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }
}
