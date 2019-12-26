package com.doraemon.support;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSupport {

    private ThreadPoolSupport() {
    }

    public static ExecutorService newCustomThreadPool(int nThreads) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1 << 10);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
    }
}
