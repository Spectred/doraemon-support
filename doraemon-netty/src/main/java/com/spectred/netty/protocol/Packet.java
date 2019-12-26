package com.spectred.netty.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SWD
 */
@Data
public abstract class Packet  {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
