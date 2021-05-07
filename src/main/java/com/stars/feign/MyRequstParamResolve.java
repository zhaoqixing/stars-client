package com.stars.feign;

import com.stars.annotation.*;
import com.stars.core.RegisteServe;
import com.stars.exception.ReqTypeNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/29 16:29
 */
public class MyRequstParamResolve extends FeignClientContext {

    public MyRequstParamResolve() {
    }


//    public String resolveUrl(Method method) throws ReqTypeNotFoundException {
//        //解析方法上请求地址
//        return resolveMethod(method);
//    }

    public void resolveType(Method method) throws ReqTypeNotFoundException {
        Get get = method.getAnnotation(Get.class);
        Post post = method.getAnnotation(Post.class);
        if (get == null && post == null) {
            throw new ReqTypeNotFoundException("没有找到请求类型，需指定GET/POST");
        }
        if (get != null) {
            requestType = RegisteServe.RequestType.GET;
            url = get.url();
            mediaType = get.produces();
//            return RegisteServe.RequestType.GET;
        } else {
            requestType = RegisteServe.RequestType.POST;
            url = post.url();
            mediaType = post.produces();
        }
        //解析方法上请求地址
//        return RequestType.POST;
    }

//    public String resolveMethod(Method method) throws ReqTypeNotFoundException {
//        Get get = method.getAnnotation(Get.class);
//        Post post = method.getAnnotation(Post.class);
//        if (get == null && post == null) {
//            throw new ReqTypeNotFoundException("没有找到请求类型，需指定GET/POST");
//        }
//        if (get != null) {
//            requestType = RequestType.GET;
//            return get.url();
//        }
//        requestType = RequestType.POST;
//        return post.url();
//    }

    //解析出入参信息
    public HashMap<String, ParamBean> convertParamBean(Method method, Object[] args){
        HashMap<String, ParamBean> paramMap = new HashMap<String, ParamBean>();
        Annotation[][] annotations = method.getParameterAnnotations();
        method.getParameterTypes();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annot : annotations[i]) {
                //获取注解名
                if (annot.annotationType() == Param.class) {
                    Param param = (Param) annot;
                    paramMap.put(param.value(), paramTypeHandle(param, args[i]));
                }
                if (annot.annotationType() == Path.class) {
                    Path path = (Path) annot;
                    paramMap.put(path.value(), pathTypeHandle(path, args[i]));
                }
                if (annot.annotationType() == Body.class) {
                    Body body = (Body) annot;
                    paramMap.put("args", pathTypeHandle(body, args[i]));
                }
            }
        }
        return paramMap;
    }

    // @Param 类型参数解析
    public ParamBean paramTypeHandle(Param annotation, Object value){
        String key = annotation.value();
        //必填
        if (annotation.required()) {
            Assert.notNull(value, key + " is required!");
        }
        return new ParamBean(key, value, annotation);
    }

    // @Path 类型参数解析
    public ParamBean pathTypeHandle(Path annotation, Object value){
        String key = annotation.value();
        //必填
        if (annotation.required()) {
            Assert.notNull(value, key + " is required!");
        }
        return new ParamBean(key, value, annotation);
    }

    // @Body 类型参数解析
    public ParamBean pathTypeHandle(Body annotation, Object value){
        String key = annotation.value();
        //必填
        if (annotation.required()) {
            Assert.notNull(value, key + " is required!");
        }
        return new ParamBean(key, value, annotation);
    }

    // 构建完整的URL
    public String completeUrl(String baseUrl, HashMap<String, ParamBean> paramMap){
        if (CollectionUtils.isEmpty(paramMap)) {
            return baseUrl;
        }
        String completeUrl = baseUrl + url;
        Set<String> set = paramMap.keySet();
        for (String s : set) {
            ParamBean paramBean = paramMap.get(s);
            if (Path.class == paramBean.getAnnotation().annotationType()) {
                completeUrl = completeUrl.trim().replace("{" + paramBean.getKey() + "}", paramBean.getValue().toString()).replace("?", "");
                paramMap.remove(s);
            }
        }
        if (CollectionUtils.isEmpty(paramMap)) {
            return completeUrl;
        }
        if (!(set.size() == 1 && set.contains("args"))) {
            completeUrl = completeUrl + "?";
        }
        for (String s : set) {
            ParamBean paramBean = paramMap.get(s);
            if (Param.class == paramBean.getAnnotation().annotationType()) {
                completeUrl = completeUrl + paramBean.getKey() + "=" + paramBean.getValue() + "/";
            }
        }
        return completeUrl;
    }
}
