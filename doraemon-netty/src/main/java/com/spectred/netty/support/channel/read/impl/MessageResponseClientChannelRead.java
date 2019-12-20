package com.spectred.netty.support.channel.read.impl;

import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.response.MessageResponsePacket;
import com.spectred.netty.support.channel.read.ChannelRead;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
public class MessageResponseClientChannelRead implements ChannelRead {
    @Override
    public void doChannelRead(ChannelHandlerContext ctx, Packet packet) {
        MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
        log.info("客户端收到服务端消息:" + messageResponsePacket.getMessage());
    }
}
