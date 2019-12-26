package com.spectred.netty.client;

import com.spectred.netty.client.handler.CreateGroupResponseHandler;
import com.spectred.netty.client.handler.LoginResponseHandler;
import com.spectred.netty.client.handler.MessageResponseHandler;
import com.spectred.netty.common.Spliter;
import com.spectred.netty.codec.PacketDecoder;
import com.spectred.netty.codec.PacketEncoder;
import com.spectred.netty.support.NettySupport;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import lombok.extern.slf4j.Slf4j;


/**
 * Netty Demo Client
 *
 * @author SWD
 */
@Slf4j
public class NettyClient {

    private static final String HOST = "localhost";

    private static final int PORT = 8080;

    private static final int RETRY = 3;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        addChannelHandler(pipeline);
                    }
                });
        NettySupport.connect(bootstrap, HOST, PORT, RETRY);
    }

    /**
     * 添加ChannelHandler
     *
     * @param pipeline ChannelPipeline
     */
    private static void addChannelHandler(ChannelPipeline pipeline) {
        // 拆包器,拒绝非本协议连接
        pipeline.addLast(new Spliter());
        // 解码器
        pipeline.addLast(new PacketDecoder());
        // 登录逻辑处理
        pipeline.addLast(new LoginResponseHandler());
        // 群聊处理
        pipeline.addLast(new CreateGroupResponseHandler());
        // 消息逻辑处理
        pipeline.addLast(new MessageResponseHandler());
        // 编码器
        pipeline.addLast(new PacketEncoder());
    }
}
