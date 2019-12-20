package com.spectred.netty.server.handler;

import com.spectred.netty.common.Session;
import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.util.LoginUtil;
import com.spectred.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        ctx.channel().writeAndFlush(login(ctx, loginRequestPacket));

        log.info("Netty服务端: 客户端登录完成. ");
    }


    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        // 登录校验
        if (valid(loginRequestPacket)) {
            // 校验成功
            LoginUtil.markAsLogin(ctx.channel());

            SessionUtil.bind(new Session(loginRequestPacket.getUserId(), loginRequestPacket.getUserName()), ctx.channel());

            loginResponsePacket.setSuccess(true);
        } else {
            // 校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
        }
        return loginResponsePacket;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 用户断线之后取消绑定
        SessionUtil.unbind(ctx.channel());
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "pwd".equals(loginRequestPacket.getPassword());
    }
}
