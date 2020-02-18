package com.doraemon.threads.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalContextTest {

    private static final int N_THREADS = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        for (int i = 0; i < N_THREADS; i++) {
            try {
                executorService.execute(() -> {
                    LocalContext.assignAttr1(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + ":"
                            + LocalContext.getInstance().getAttr1().equals(Thread.currentThread().getName()));
                });
            } finally {
                LocalContext.remove();
            }

        }

        executorService.shutdown();
        System.out.println(executorService.isShutdown() ? "Success" : "Failed");

    }
}
