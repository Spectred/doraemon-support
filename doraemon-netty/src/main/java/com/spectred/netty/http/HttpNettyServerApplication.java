package com.spectred.netty.http;

public class HttpNettyServerApplication {

    public static void main(String[] args) throws InterruptedException {
        new HttpNettyServer(8081).start();
    }
}
