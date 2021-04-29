package com.stars.config.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/7 16:42
 */
public class NettyClient {

    private static String inetHost = "127.0.0.1";

    private static Integer inetPort = 8090;

    public static Channel build(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect(inetHost, inetPort).sync();
            System.out.println("客户端成功....");
            Channel channel = future.channel();
            return channel;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } /*finally {
            group.shutdownGracefully();
        }*/
    }

}
