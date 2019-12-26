package com.spectred.netty.client.console;

import com.spectred.netty.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("接收消息的用户ID: ");
        String toUserId = sc.next();
        System.out.println("输入消息内容: ");
        String message = sc.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
