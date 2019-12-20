package com.spectred.netty.support;

import com.spectred.netty.support.channel.read.impl.DefaultChannelRead;
import com.spectred.netty.support.channel.read.impl.LoginRequestServerChannelRead;
import com.spectred.netty.support.channel.read.impl.LoginResponseClientChannelRead;
import com.spectred.netty.support.channel.read.ChannelRead;
import com.spectred.netty.support.channel.read.impl.MessageRequestServerChannelRead;
import com.spectred.netty.support.channel.read.impl.MessageResponseClientChannelRead;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static com.spectred.netty.protocol.command.Command.*;

@Deprecated
public class ChannelReadSupport {

    private ChannelReadSupport() {
    }

    private static final Map<Byte, Supplier<ChannelRead>> CHANNEL_READ_MAP;

    static {
        CHANNEL_READ_MAP = new ConcurrentHashMap<>(1 << 4);
        CHANNEL_READ_MAP.put(DEFAULT, DefaultChannelRead::new);
        CHANNEL_READ_MAP.put(LOGIN_REQUEST, LoginRequestServerChannelRead::new);
        CHANNEL_READ_MAP.put(LOGIN_RESPONSE, LoginResponseClientChannelRead::new);
        CHANNEL_READ_MAP.put(MESSAGE_REQUEST, MessageRequestServerChannelRead::new);
        CHANNEL_READ_MAP.put(MESSAGE_RESPONSE, MessageResponseClientChannelRead::new);
    }

    public static ChannelRead get(Byte command) {
        Supplier<ChannelRead> supplier = CHANNEL_READ_MAP.get(command);
        if (Objects.isNull(supplier)) {
            command = DEFAULT;
        }
        return CHANNEL_READ_MAP.get(command).get();
    }
}
