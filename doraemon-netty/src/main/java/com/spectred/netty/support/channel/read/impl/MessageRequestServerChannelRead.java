package com.spectred.netty.support.channel.read.impl;

import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.PacketCodeC;
import com.spectred.netty.protocol.request.MessageRequestPacket;
import com.spectred.netty.protocol.response.MessageResponsePacket;
import com.spectred.netty.support.channel.read.ChannelRead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
public class MessageRequestServerChannelRead implements ChannelRead {
    @Override
    public void doChannelRead(ChannelHandlerContext ctx, Packet packet) {
        MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

        MessageResponsePacket messageResponsePacket = receiveMessage(messageRequestPacket);

        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);

    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        log.info("服务端收到客户端消息:" + messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("收到");
        return messageResponsePacket;
    }
}
