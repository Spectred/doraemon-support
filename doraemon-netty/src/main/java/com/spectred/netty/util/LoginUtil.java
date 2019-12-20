package com.spectred.netty.util;

import com.spectred.netty.common.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

    private LoginUtil() {
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(Boolean.TRUE);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}
