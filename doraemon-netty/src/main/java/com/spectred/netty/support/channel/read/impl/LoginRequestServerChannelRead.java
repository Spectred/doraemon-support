package com.spectred.netty.support.channel.read.impl;

import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.PacketCodeC;
import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.support.channel.read.ChannelRead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
public class LoginRequestServerChannelRead implements ChannelRead {
    @Override
    public void doChannelRead(ChannelHandlerContext ctx, Packet packet) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);

        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
        log.info("Netty服务端 登录完成");
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        // 登录校验
        if (valid(loginRequestPacket)) {
            // 校验成功
            loginResponsePacket.setSuccess(true);
        } else {
            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
        }
        return loginResponsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
