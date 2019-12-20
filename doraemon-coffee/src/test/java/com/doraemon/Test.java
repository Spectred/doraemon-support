package com.doraemon;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    private static final Integer THREAD_COUNT = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executors.newCachedThreadPool();
        LinkedBlockingDeque<Runnable> callables = new LinkedBlockingDeque<>(1024);
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("mypool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, callables, build, abortPolicy);

        CountDownLatch cdl = new CountDownLatch(THREAD_COUNT);

        long l = System.currentTimeMillis();
        CopyOnWriteArrayList<Future<Integer>> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Future<Integer> submit = threadPoolExecutor.submit(new MyCallable(cdl, i));
            list.add(submit);
        }

        cdl.await();
        System.out.println(System.currentTimeMillis()-l);

        threadPoolExecutor.shutdown();

        Integer sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Future<Integer> integerFuture = list.get(i);
            sum += integerFuture.get();
        }

        System.out.println(sum);


    }
}
