package com.spectred.netty.protocol.response;

import com.spectred.netty.protocol.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;

/**
 * @author SWD
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
