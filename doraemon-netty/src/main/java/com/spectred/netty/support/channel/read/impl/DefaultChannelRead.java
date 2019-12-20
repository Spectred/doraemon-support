package com.spectred.netty.support.channel.read.impl;

import com.spectred.netty.protocol.Packet;
import com.spectred.netty.support.channel.read.ChannelRead;
import io.netty.channel.ChannelHandlerContext;
@Deprecated
public class DefaultChannelRead implements ChannelRead {
    @Override
    public void doChannelRead(ChannelHandlerContext ctx, Packet packet) {
        System.out.println("DO NOTHING");
    }
}
