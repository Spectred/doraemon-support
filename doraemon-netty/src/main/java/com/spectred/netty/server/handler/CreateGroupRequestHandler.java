package com.spectred.netty.server.handler;

import com.spectred.netty.common.Session;
import com.spectred.netty.protocol.request.CreateGroupRequestPacket;
import com.spectred.netty.protocol.response.CreateGroupResponsePacket;
import com.spectred.netty.util.IdUtils;
import com.spectred.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 创建群聊处理
 *
 * @author SWD
 */
@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket requestPacket) throws Exception {
        List<String> userIdList = requestPacket.getUserIdList();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();

        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (Objects.nonNull(channel)) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });

        String id = IdUtils.getId();
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(id);
        responsePacket.setSuccess(true);
        responsePacket.setUserNameList(userNameList);

        SessionUtil.bindChannelGroup(id,channelGroup);
        channelGroup.writeAndFlush(responsePacket);

        log.info("群组创建成功. Id=[{}],UserNameList={}",id,userNameList);

    }
}
