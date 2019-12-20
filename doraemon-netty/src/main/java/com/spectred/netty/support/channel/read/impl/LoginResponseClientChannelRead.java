package com.spectred.netty.support.channel.read.impl;

import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.support.channel.read.ChannelRead;
import com.spectred.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
public class LoginResponseClientChannelRead implements ChannelRead {
    @Override
    public void doChannelRead(ChannelHandlerContext ctx, Packet packet) {
        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            log.info("客户端登录成功");
        } else {
            log.error("客户端登录失败，原因:{}", loginResponsePacket.getReason());
        }
    }
}
