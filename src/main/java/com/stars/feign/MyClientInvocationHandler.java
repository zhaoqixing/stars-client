package com.stars.feign;

import com.stars.handler.send.RegistrarServerManager;

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
        String url = resolveUrl(method, args);
        //解析参数
        HashMap<String, ParamBean> paramBeanHashMap = convertParamBean(method, args);
        String completeUrl = completeUrl(baseUrl, url, paramBeanHashMap);
        System.out.println("即将发送请求地址：" + completeUrl);
        return null;
    }
    //

    //拼接请求地址

    //加入参数

    //选择调用策略

    //处理返回结果


}
