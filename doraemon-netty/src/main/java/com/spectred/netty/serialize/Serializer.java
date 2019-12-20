package com.spectred.netty.serialize;

/**
 * @author SWD
 */
public interface Serializer {

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
