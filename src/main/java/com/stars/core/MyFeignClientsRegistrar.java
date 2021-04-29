package com.stars.core;

import com.stars.annotation.MyFeignClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:08
 */
public class MyFeignClientsRegistrar implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(false){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isInterface()) {
                    return true ;
                }
                return super.isCandidateComponent(beanDefinition);
            }
        };
        //扫描MyFeignClient 的接口
        componentProvider.addIncludeFilter(new AnnotationTypeFilter(MyFeignClient.class));

        try {
            String scanPackage = Class.forName(annotationMetadata.getClassName()).getPackage().getName();
            Set<BeanDefinition> definitions = componentProvider.findCandidateComponents(scanPackage);

            for (BeanDefinition beanDefinition : definitions){
                AnnotatedBeanDefinition adb = (AnnotatedBeanDefinition) beanDefinition;
                String baseUrl = (String) adb.getMetadata().getAnnotationAttributes(MyFeignClient.class.getName()).get("baseUrl");
                registerClientConfiguration(Class.forName(beanDefinition.getBeanClassName()), baseUrl, beanDefinitionRegistry);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerClientConfiguration(Object type, Object baseUrl,  BeanDefinitionRegistry registry){
        String defaultName = type.toString().substring(type.toString().lastIndexOf(".") + 1);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(MyFactoryBean.class);
        builder.addConstructorArgValue(baseUrl);
        builder.addConstructorArgValue(type);
        registry.registerBeanDefinition(canonicalName(defaultName), builder.getBeanDefinition());
    }

    private String canonicalName(String name){
        char[] chars = name.toCharArray();
        String s = String.valueOf(chars[0]).toLowerCase();
        chars [0] = s.charAt(0);
        return new String(chars);
    }
}
