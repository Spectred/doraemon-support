package com.spectred.netty.util;

import com.spectred.netty.support.SnowFlakeSupport;


public class IdUtils {
    private IdUtils() {

    }

    public static String getId() {
        return String.valueOf(new SnowFlakeSupport(2L, 3L).nextId());
    }
}
