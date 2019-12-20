package com.spectred.netty.client.handler;

import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String userId = String.valueOf(new Random().nextInt(10));
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(userId);
        loginRequestPacket.setUserName("Some"+userId);
        loginRequestPacket.setPassword("pwd");
        // 验证非登录状态删除该行
        ctx.channel().writeAndFlush(loginRequestPacket);
        log.warn("userId=[{}]", loginRequestPacket.getUserId());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            log.info("客户端登录成功");
        } else {
            log.error("客户端登录失败，原因:{}", loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.warn("客户端连接被关闭!");
    }
}
