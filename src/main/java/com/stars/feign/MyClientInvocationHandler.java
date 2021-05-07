package com.stars.feign;

import com.alibaba.fastjson.JSON;
import com.stars.core.RegisteServe;
import com.stars.handler.send.RegistrarServerManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:18
 */
public class MyClientInvocationHandler extends MyRequstParamResolve implements InvocationHandler {

    private String baseUrl;

    private final RegistrarServerManager serverManager = new RegistrarServerManager();

    public MyClientInvocationHandler(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    //进行代理调用 -> 进行远程调用
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        resolveType(method);
        //解析参数
        HashMap<String, ParamBean> paramBeanHashMap = convertParamBean(method, args);
        String completeUrl = completeUrl(baseUrl, paramBeanHashMap);
        System.out.println("即将发送请求地址：" + completeUrl);
        return sendReq(paramBeanHashMap, completeUrl);
    }
    //

    //
    public Object sendReq(HashMap<String, ParamBean> param, String completeUrl){
        if (RegisteServe.RequestType.GET == requestType) {
            HttpGet get = new HttpGet(completeUrl);
            return execute(get);
        }
        if (RegisteServe.RequestType.POST == requestType) {
            HttpPost post = new HttpPost(completeUrl);
            ParamBean paramBean = param.get("args");
            if (!ObjectUtils.isEmpty(paramBean)) {
                String body = JSON.toJSONString(paramBean.getValue());
                StringEntity stringEntity = new StringEntity(body,"UTF-8");
                post.setEntity(stringEntity);
                System.out.println("param :" + body);
                if (StringUtils.hasText(mediaType)) {
                    post.setHeader("Content-Type", mediaType);
                }
            }

            return execute(post);
        }
        throw new RuntimeException("不支持的请求类型 " + requestType);
    }

    public HttpEntity execute(HttpRequestBase requestBase){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(requestBase);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (entity != null) {
                System.out.println("响应内容为:" + EntityUtils.toString(entity));
            }
            return entity;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //加入参数

    //选择调用策略

    //处理返回结果


}
