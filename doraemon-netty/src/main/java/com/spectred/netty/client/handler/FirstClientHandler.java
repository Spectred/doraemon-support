package com.spectred.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * 第一个逻辑处理器
 *
 * @author SWD
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(LocalDate.now() + ": 客户端写出数据");
            // 获取数据
            ByteBuf byteBuf = getByteBuf(ctx);
            // 写数据
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDate.now() + ":客户端接收到消息:" + byteBuf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 准备数据
        byte[] bytes = "HelloWorld".getBytes(StandardCharsets.UTF_8);
        // 填充数据到buffer
        buffer.writeBytes(bytes);
        return buffer;
    }
}
