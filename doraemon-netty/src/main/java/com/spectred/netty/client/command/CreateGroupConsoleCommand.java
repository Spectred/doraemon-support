package com.spectred.netty.client.command;

import com.spectred.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userIds = scanner.next();
        List<String> userIdList = Arrays.asList(userIds.split(","));
        log.info("群聊用户ID={}", userIdList);
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(userIdList);

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
