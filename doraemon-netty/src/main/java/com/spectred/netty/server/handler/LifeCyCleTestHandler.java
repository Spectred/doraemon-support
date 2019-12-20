package com.spectred.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * 生命周期测试类
 *
 * @author SWD
 */
@Slf4j
public class LifeCyCleTestHandler extends ChannelInboundHandlerAdapter {
    /*
     * 新建连接: handlerAdded -> channelRegistered -> channelActive -> channelRead -> channelReadComplete
     * 关闭连接: channelReadComplete -> channelInactive -> channelUnregistered -> handlerRemoved
     */

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.warn("-> channelRead");
        super.channelRead(ctx, msg);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.warn("-> handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
