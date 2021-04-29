package com.test;

import com.stars.App;
import com.stars.testclient.ReqClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 16:40
 */
public class Test1 {

    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
        ReqClient reqClient = (ReqClient)applicationContext.getBean("reqClient");
        reqClient.testReq();
        reqClient.postReq("ok", "ok2");
        reqClient.pathReq( "10", "zhangsan");
    }
}
