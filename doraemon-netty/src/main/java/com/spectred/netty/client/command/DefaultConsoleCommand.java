package com.spectred.netty.client.command;

import com.spectred.netty.client.command.ConsoleCommand;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class DefaultConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.warn("无法识别指令:[{}]", scanner.next());
    }
}
