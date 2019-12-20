package com.spectred.netty.client.handler;

import com.spectred.netty.support.channel.read.ChannelRead;
import com.spectred.netty.support.ChannelReadSupport;
import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author SWD
 */
@Slf4j(topic = "ClientHandler")
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端连接上服务端后,会回调到该方法
        log.info("客户端开始登录");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString())
                .setUserName("yourName")
                .setPassword("yourPwd");
        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        ChannelRead channelRead = ChannelReadSupport.get(packet.getCommand());
        channelRead.doChannelRead(ctx, packet);
    }
}
