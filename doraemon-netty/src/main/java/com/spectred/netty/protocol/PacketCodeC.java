package com.spectred.netty.protocol;

import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.protocol.request.MessageRequestPacket;
import com.spectred.netty.protocol.response.MessageResponsePacket;
import com.spectred.netty.serialize.impl.JSONSerializer;
import com.spectred.netty.serialize.Serializer;
import com.spectred.netty.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.spectred.netty.protocol.command.Command.LOGIN_REQUEST;
import static com.spectred.netty.protocol.command.Command.LOGIN_RESPONSE;
import static com.spectred.netty.protocol.command.Command.MESSAGE_REQUEST;
import static com.spectred.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author SWD
 */
public class PacketCodeC {
    private PacketCodeC() {
    }

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> PACKET_TYPE_MAP;
    private static final Map<Byte, Serializer> SERIALIZER_MAP;

    static {
        PACKET_TYPE_MAP = new HashMap<>();
        PACKET_TYPE_MAP.put(LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_TYPE_MAP.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_TYPE_MAP.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_TYPE_MAP.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        SERIALIZER_MAP = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator alloc, Packet packet) {
        // 1. 创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2. 序列化JavaBean
        byte[] bytes = SerializerAlgorithm.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化JavaBean
        byte[] bytes = SerializerAlgorithm.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过MagicNumber
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (Objects.nonNull(requestType) && Objects.nonNull(serializer)) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }


    private Serializer getSerializer(byte serializeAlgorithm) {

        return SERIALIZER_MAP.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return PACKET_TYPE_MAP.get(command);
    }
}
