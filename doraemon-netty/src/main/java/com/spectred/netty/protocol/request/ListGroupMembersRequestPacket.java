package com.spectred.netty.protocol.request;

import com.spectred.netty.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;


@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
