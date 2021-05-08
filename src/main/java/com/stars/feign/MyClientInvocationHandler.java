package com.stars.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.stars.core.RegisteServe;
import com.stars.exception.MyJSONException;
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
    private String serveName;
    public MyClientInvocationHandler(String baseUrl, String serveName) {
        this.baseUrl = baseUrl;
        this.serveName = serveName;
    }

    //进行代理调用 -> 进行远程调用
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        resolveType(method);
        // 匹配策略
        matchHost();
        //解析参数
        HashMap<String, ParamBean> paramBeanHashMap = convertParamBean(method, args);
        String completeUrl = completeUrl(baseUrl, paramBeanHashMap);
        String respStr = sendReq(paramBeanHashMap, completeUrl);
        return this.handlerResp(respStr, method.getReturnType());
    }
    //

    //
    public String sendReq(HashMap<String, ParamBean> param, String completeUrl){
        if (RegisteServe.RequestType.GET == requestType) {
            HttpGet get = new HttpGet(completeUrl);
            return execute(get);
        }
        if (RegisteServe.RequestType.POST == requestType) {
            HttpPost post = new HttpPost(completeUrl);
            ParamBean paramBean = param.get("args");
            if (!ObjectUtils.isEmpty(paramBean)) {
                String body = JSON.toJSONString(paramBean.getValue());
                StringEntity stringEntity = new StringEntity(body,"utf-8");
                post.setEntity(stringEntity);
                if (StringUtils.hasText(mediaType)) {
                    post.setHeader("Content-Type", mediaType);
                }
            }
            return execute(post);
        }
        throw new RuntimeException("不支持的请求类型 " + requestType);
    }

    public String execute(HttpRequestBase requestBase){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(requestBase);
            logger.info("响应状态为:" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
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
        }
        return "";
    }

    //加入参数


    //处理返回结果
    private Object handlerResp(String resp, Class<?> returnType) throws IllegalAccessException, InstantiationException {
        if (StringUtils.hasText(resp)) {
            if (returnType == String.class) {
                return resp;
            } else if (returnType == void.class){
                return null;
            }
            try{
                return JSONObject.parseObject(resp, returnType);
            } catch (JSONException e){
                throw new MyJSONException("response Can't be transformed to " + returnType + " [response is " + resp + "]");
            }
        }
        return returnType.newInstance();
    }


    private void matchHost () {
        // 优先使用baseUrl
        if (StringUtils.hasText(baseUrl)) {
            return;
        }
        // todo serveName 不为空，去备份的注册服务寻找主机


    }

    //选择调用策略
}
