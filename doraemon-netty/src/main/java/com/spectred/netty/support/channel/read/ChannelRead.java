package com.spectred.netty.support.channel.read;

import com.spectred.netty.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;

@Deprecated
public interface ChannelRead {

    void doChannelRead(ChannelHandlerContext ctx, Packet packet);
}
