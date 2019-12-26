package com.spectred.netty.protocol.response;

import com.spectred.netty.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.QUIT_GROUP_RESPONSE;
    }
}
