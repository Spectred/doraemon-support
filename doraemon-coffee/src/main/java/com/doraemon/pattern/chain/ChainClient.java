package com.doraemon.pattern.chain;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class ChainClient {

    public static void main(String[] args) {

        new Thread(() -> {
            Chain chain = new Chain();
            chain.next(new ConcreteHandler1())
                    .next(new ConcreteHandler2())
                    .next(new ConcreteHandler3());
            String msg = "A";
            String str = chain.handle(msg);
            System.out.println(Thread.currentThread().getName() + "-" + str);
            ChainSupport.remove();
        }, "A").start();

        new Thread(() -> {
            Chain chain = new Chain();
            chain.next(new ConcreteHandler1())
                    .next(new ConcreteHandler2())
                    .next(new ConcreteHandler3());
            String msg = "A";
            String str = chain.handle(msg);
            System.out.println(Thread.currentThread().getName() + "-" + str);
            ChainSupport.remove();
        }, "B").start();

        new Thread(() -> {
            Chain chain = new Chain();
            chain.next(new ConcreteHandler1())
                    .next(new ConcreteHandler2())
                    .next(new ConcreteHandler3());
            String msg = "A";
            String str = chain.handle(msg);
            System.out.println(Thread.currentThread().getName() + "-" + str);
            ChainSupport.remove();
        }, "C").start();


    }
}
