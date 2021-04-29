package com.stars.handler.send;

import com.stars.core.RegisteServe;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:50
 */
public class RegistrarServerManager {
    private static List<RegisteServe> registeServeBeans = new ArrayList<RegisteServe>();
    private static Channel channel;

    static {
        // TODO: 2021/4/29 建立netty客户端连接
        System.out.println("todo 建立netty客户端连接");
//        channel = NettyClient.build();
    }

    public void registrarServer(){
        channel.writeAndFlush("hello");
    }

    public void syncHeartbeat(){

    }

    public void offline(){

    }
}
