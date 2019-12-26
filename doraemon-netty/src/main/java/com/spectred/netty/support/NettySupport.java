package com.spectred.netty.support;

import com.spectred.netty.client.console.LoginConsoleCommand;
import com.spectred.netty.client.console.ConsoleCommandManager;
import com.spectred.netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Netty Support
 *
 * @author SWD
 */
@Slf4j
public class NettySupport {

    private static final int MAX_RETRY = 5;

    private NettySupport() {
    }

    /**
     * 服务端绑定端口
     *
     * @param serverBootstrap serverBootstrap
     * @param port            port
     */
    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("Netty服务端绑定端口成功,port=[{}]", port);
            } else {
                log.warn("Netty服务端绑定端口失败,port=[{}],尝试绑定下一端口", port);
                bind(serverBootstrap, port + 1);
            }
        });
    }


    /**
     * 客户端连接服务端
     *
     * @param bootstrap bootstrap
     * @param host      host
     * @param port      port
     * @param retry     retry
     */
    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        GenericFutureListener<? extends Future<? super Void>> retryListener = future -> {
            if (future.isSuccess()) {
                log.info("Netty客户端 成功连接服务端:[{}:{}]", host, port);
            } else if (retry == 0) {
                log.error("重连次数用完，连接失败,退出程序.");
                System.exit(1);
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = order << 1;
                log.warn("剩余重连次数:{},间隔时间:{}", retry - 1, delay);
                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS
                );
            }
        };
        GenericFutureListener<? extends Future<? super Void>> consoleListener = future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            }
        };

        bootstrap.connect(host, port).addListener(retryListener).addListener(consoleListener);
    }


    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        Scanner sc = new Scanner(System.in);
        Runnable runnable = () -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    consoleCommandManager.exec(sc, channel);
                } else {
                    loginConsoleCommand.exec(sc, channel);
                }
            }
        };

        ExecutorService executorService = ThreadPoolSupport.newCustomThreadPool(1);
        executorService.execute(runnable);
    }
}
