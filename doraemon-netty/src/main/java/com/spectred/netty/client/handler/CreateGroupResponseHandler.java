package com.spectred.netty.client.handler;

import com.spectred.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket responsePacket) throws Exception {
        log.info("群聊创建是否创建成功=[{}],id=[{}],userNameList={}", responsePacket.getSuccess(), responsePacket.getGroupId(), responsePacket.getUserNameList());
    }
}
