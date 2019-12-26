package com.spectred.netty.client.command;

import com.spectred.netty.client.command.ConsoleCommand;
import com.spectred.netty.client.command.CreateGroupConsoleCommand;
import com.spectred.netty.client.command.DefaultConsoleCommand;
import com.spectred.netty.client.command.LogoutConsoleCommand;
import com.spectred.netty.client.command.SendToUserConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>(1 << 4);
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.getOrDefault(command, new DefaultConsoleCommand());
        consoleCommand.exec(scanner, channel);
    }
}
