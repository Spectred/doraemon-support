package com.spectred.netty.protocol.response;

import com.spectred.netty.command.Command;
import com.spectred.netty.common.Session;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
