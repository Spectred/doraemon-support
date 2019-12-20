package com.spectred.netty.server.handler;

import com.spectred.netty.util.LoginUtil;
import com.spectred.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            log.info("客户端登录已验证");
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())){
            log.info("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        }else {
            log.warn("无登录验证，强制关闭连接!");
        }
    }
}
