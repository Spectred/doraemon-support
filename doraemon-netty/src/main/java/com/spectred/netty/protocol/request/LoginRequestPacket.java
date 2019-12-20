package com.spectred.netty.protocol.request;

import com.spectred.netty.protocol.command.Command;
import com.spectred.netty.protocol.Packet;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author SWD
 */
@Data
@Accessors(chain = true)
public class LoginRequestPacket extends Packet {

    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
