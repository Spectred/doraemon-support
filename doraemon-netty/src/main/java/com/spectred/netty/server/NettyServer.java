package com.spectred.netty.server;

import com.spectred.netty.codec.PacketDecoder;
import com.spectred.netty.codec.PacketEncoder;
import com.spectred.netty.common.Spliter;
import com.spectred.netty.server.handler.AuthHandler;
import com.spectred.netty.server.handler.FirstServerHandler;
import com.spectred.netty.server.handler.LifeCyCleTestHandler;
import com.spectred.netty.server.handler.LoginRequestHandler;
import com.spectred.netty.server.handler.MessageRequestHandler;
import com.spectred.netty.server.handler.ServerHandler;
import com.spectred.netty.support.NettySupport;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty Demo Server
 *
 * @author SWD
 */
@Slf4j
public class NettyServer {

    private static final int BIND_PORT = 8080;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        addChannelHandler(pipeline);
                    }
                });
        NettySupport.bind(serverBootstrap,BIND_PORT);
    }


    /**
     * 添加ChannelHandler
     *
     * @param pipeline ChannelPipeline
     */
    private static void addChannelHandler(ChannelPipeline pipeline) {
        // channelHandler生命周期测试
        // pipeline.addLast(new LifeCyCleTestHandler());
        // channelHandler demo
        // pipeline.addLast(new FirstServerHandler());
        // channelHandler demo
        //pipeline.addLast(new ServerHandler());

        // 拆包器,拒绝非本协议连接
        pipeline.addLast(new Spliter());
        // 解码器
        pipeline.addLast(new PacketDecoder());
        // 登录逻辑处理
        pipeline.addLast(new LoginRequestHandler());
        // 权限验证逻辑处理
        pipeline.addLast(new AuthHandler());
        // 消息逻辑处理
        pipeline.addLast(new MessageRequestHandler());
        // 编码器
        pipeline.addLast(new PacketEncoder());
    }

}
