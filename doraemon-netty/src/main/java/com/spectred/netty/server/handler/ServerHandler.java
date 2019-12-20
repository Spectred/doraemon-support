package com.spectred.netty.server.handler;

import com.spectred.netty.support.channel.read.ChannelRead;
import com.spectred.netty.support.ChannelReadSupport;
import com.spectred.netty.protocol.Packet;
import com.spectred.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author SWD
 */
@Slf4j(topic = "ServerHandler")
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        ChannelRead channelRead = ChannelReadSupport.get(packet.getCommand());
        channelRead.doChannelRead(ctx, packet);
    }

}
