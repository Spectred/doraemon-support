package com.spectred.netty.serialize.impl;


import com.alibaba.fastjson.JSON;
import com.spectred.netty.serialize.Serializer;
import com.spectred.netty.serialize.SerializerAlgorithm;

/**
 * @author SWD
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
