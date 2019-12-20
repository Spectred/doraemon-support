package com.spectred.netty.serialize;

import com.spectred.netty.serialize.impl.JSONSerializer;

/**
 * @author SWD
 */
public interface SerializerAlgorithm {

    /**
     * json 序列化标识
     */
    byte JSON_SERIALIZER = 1;

    /**
     * 默认序列化算法
     */
    Serializer DEFAULT = new JSONSerializer();
}
