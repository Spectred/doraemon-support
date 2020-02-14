package com.spectred.netty.server;

import com.spectred.netty.codec.PacketDecoder;
import com.spectred.netty.codec.PacketEncoder;
import com.spectred.netty.common.Spliter;
import com.spectred.netty.server.handler.AuthHandler;
import com.spectred.netty.server.handler.CreateGroupRequestHandler;
import com.spectred.netty.server.handler.GroupMessageRequestHandler;
import com.spectred.netty.server.handler.IMHandler;
import com.spectred.netty.server.handler.JoinGroupRequestHandler;
import com.spectred.netty.server.handler.ListGroupMembersRequestHandler;
import com.spectred.netty.server.handler.LoginRequestHandler;
import com.spectred.netty.server.handler.MessageRequestHandler;
import com.spectred.netty.server.handler.PacketCodecHandler;
import com.spectred.netty.server.handler.QuitGroupRequestHandler;
import com.spectred.netty.support.NettySupport;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
                        addChannelHandlers(pipeline);
                    }
                });
        NettySupport.bind(serverBootstrap, BIND_PORT);
    }


    /**
     * 添加ChannelHandler
     *
     * @param pipeline ChannelPipeline
     */
    private static void addChannelHandlers(ChannelPipeline pipeline) {
        // 拆包器,拒绝非本协议连接
        pipeline.addLast(new Spliter());

        pipeline.addLast(PacketCodecHandler.INSTANCE);
        // 解码器
//        pipeline.addLast(new PacketDecoder());
        // 登录逻辑处理
        pipeline.addLast(LoginRequestHandler.INSTANCE);
        // 权限验证逻辑处理
        pipeline.addLast(AuthHandler.INSTANCE);

        pipeline.addLast(IMHandler.INSTANCE);

        // 创建群聊逻辑处理
//        pipeline.addLast(CreateGroupRequestHandler.INSTANCE);
//        // 加入群聊逻辑处理
//        pipeline.addLast(JoinGroupRequestHandler.INSTANCE);
//        // 退群
//        pipeline.addLast(QuitGroupRequestHandler.INSTANCE);
//        // 群组用户列表
//        pipeline.addLast(ListGroupMembersRequestHandler.INSTANCE);
//        // 群消息
//        pipeline.addLast(GroupMessageRequestHandler.INSTANCE);
//        // 消息逻辑处理
        pipeline.addLast(MessageRequestHandler.INSTANCE);
        // 编码器
//        pipeline.addLast(new PacketEncoder());
    }

}
