package com.spectred.netty.client.handler;

import com.spectred.netty.common.Session;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();
        if (loginResponsePacket.isSuccess()) {
            log.info("客户端登录成功,userId=[{}]", userId);
            SessionUtil.bind(new Session(userId, userName), ctx.channel());
        } else {
            log.error("客户端登录失败，原因:{}", loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.warn("客户端连接被关闭!");
    }
}
