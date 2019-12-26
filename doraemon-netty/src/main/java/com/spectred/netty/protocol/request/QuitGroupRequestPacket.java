package com.spectred.netty.protocol.request;

import com.spectred.netty.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {

        return  Command.QUIT_GROUP_REQUEST;
    }
}
