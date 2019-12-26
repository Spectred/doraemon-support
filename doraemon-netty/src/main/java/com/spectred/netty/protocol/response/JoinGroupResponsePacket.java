package com.spectred.netty.protocol.response;

import com.spectred.netty.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
