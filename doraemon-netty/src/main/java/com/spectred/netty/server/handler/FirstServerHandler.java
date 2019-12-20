package com.spectred.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * @author SWD
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDate.now() + "-服务端读取到的数据:" + byteBuf.toString(StandardCharsets.UTF_8));

        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "哈哈哈哈".getBytes(StandardCharsets.UTF_8);

        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }

}
