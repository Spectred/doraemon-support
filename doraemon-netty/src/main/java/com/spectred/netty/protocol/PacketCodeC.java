package com.spectred.netty.protocol;

import com.spectred.netty.protocol.request.CreateGroupRequestPacket;
import com.spectred.netty.protocol.request.GroupMessageRequestPacket;
import com.spectred.netty.protocol.request.JoinGroupRequestPacket;
import com.spectred.netty.protocol.request.ListGroupMembersRequestPacket;
import com.spectred.netty.protocol.request.LoginRequestPacket;
import com.spectred.netty.protocol.request.QuitGroupRequestPacket;
import com.spectred.netty.protocol.response.CreateGroupResponsePacket;
import com.spectred.netty.protocol.response.GroupMessageResponsePacket;
import com.spectred.netty.protocol.response.JoinGroupResponsePacket;
import com.spectred.netty.protocol.response.ListGroupMembersResponsePacket;
import com.spectred.netty.protocol.response.LoginResponsePacket;
import com.spectred.netty.protocol.request.MessageRequestPacket;
import com.spectred.netty.protocol.response.MessageResponsePacket;
import com.spectred.netty.protocol.response.QuitGroupResponsePacket;
import com.spectred.netty.serialize.impl.JSONSerializer;
import com.spectred.netty.serialize.Serializer;
import com.spectred.netty.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.spectred.netty.command.Command.CREATE_GROUP_REQUEST;
import static com.spectred.netty.command.Command.CREATE_GROUP_RESPONSE;
import static com.spectred.netty.command.Command.GROUP_MESSAGE_REQUEST;
import static com.spectred.netty.command.Command.GROUP_MESSAGE_RESPONSE;
import static com.spectred.netty.command.Command.JOIN_GROUP_REQUEST;
import static com.spectred.netty.command.Command.JOIN_GROUP_RESPONSE;
import static com.spectred.netty.command.Command.LIST_GROUP_MEMBERS_REQUEST;
import static com.spectred.netty.command.Command.LIST_GROUP_MEMBERS_RESPONSE;
import static com.spectred.netty.command.Command.LOGIN_REQUEST;
import static com.spectred.netty.command.Command.LOGIN_RESPONSE;
import static com.spectred.netty.command.Command.MESSAGE_REQUEST;
import static com.spectred.netty.command.Command.MESSAGE_RESPONSE;
import static com.spectred.netty.command.Command.QUIT_GROUP_REQUEST;
import static com.spectred.netty.command.Command.QUIT_GROUP_RESPONSE;

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
        PACKET_TYPE_MAP.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        PACKET_TYPE_MAP.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        PACKET_TYPE_MAP.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKET_TYPE_MAP.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);

        SERIALIZER_MAP = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化JavaBean
        byte[] bytes = SerializerAlgorithm.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
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
