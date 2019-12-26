package com.spectred.netty.protocol.response;

import com.spectred.netty.command.Command;
import com.spectred.netty.common.Session;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

import java.util.List;


@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
