package com.doraemon.threads.pool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java常见线程池{@link java.util.concurrent.Executors}-测试类
 *
 * @author SWD
 */
public class ExecutorsTest {

    public static void main(String[] args) throws InterruptedException {
//        testNewFixedThreadPool(3);
//        testNewSingleThreadExecutor();
//        testNewCachedThreadPool();
        testNewScheduledThreadPool(5);

    }

    /**
     * 固定线程数量的线程池
     * 特点:
     * 1. 核心线程数和最大线程数一样
     * 2. 达到核心线程数后,空闲线程不会超时被终止或释放
     * 3. 每添加一个任务,会将任务添加到工作任务队列,线程池创建一个线程,线程数等于核心线程数时,就不会创建线程
     *
     * @param nThreads 线程数
     */
    private static void testNewFixedThreadPool(int nThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        try {
            printThreadName(executorService);
        } finally {
            executorService.shutdown();
        }
        System.out.println(executorService.isShutdown());
    }


    /**
     * 单线程的线程池
     * 特点:
     * 1. 核心线程数和最大线程数一样,且为1,只有一个线程在执行队列的工作任务
     * 2. 使用了代理模式来创建线程
     */
    public static void testNewSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
            printThreadName(executorService);
        } finally {
            executorService.shutdown();
        }
        System.out.println(executorService.isShutdown());
    }

    /**
     * 可缓存的线程池
     * 特点:
     * 1. 核心线程数为0,最大线程数为Int的最大值,空闲线程可以缓存60s,空闲超过60s的线程会被回收
     * 2. 使用SynchronousQueue同步队列,添加任务的通是，需有工作线程来取任务才可以完成任务的添加和执行
     */
    private static void testNewCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            printThreadName(executorService);
        } finally {
            executorService.shutdown();
        }
        System.out.println(executorService.isShutdown());
    }

    /**
     * 定时执行的线程池
     * 特点:
     * 1. 可指定核心线程数，最大线程数为Int最大值，核心线程数空闲不会被回收
     * 2. 使用DelayedWorkQueue延时队列，通过延时队列来控制时间来执行
     *
     * @param corePoolSize
     */
    private static void testNewScheduledThreadPool(int corePoolSize) {
        var executorService = Executors.newScheduledThreadPool(corePoolSize);
        System.out.println(LocalDateTime.now());

        executorService.scheduleAtFixedRate(
                () -> System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName()),
                3, 5, TimeUnit.SECONDS);

        System.out.println(executorService.isShutdown());
    }


    private static void printThreadName(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }
}
