package com.doraemon.threads.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的四种方式
 *
 * @author SWD
 */
public class CreateThreads {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        createThreadByRunnable();
//        createThreadByThread();
//        createThreadByCallable();
        createThreadByPool();
    }

    /**
     * 通过Runnable接口创建线程
     * 1. 重写Runnable的run方法
     * 2. 使用Runnable对象构造Thread对象
     */
    private static void createThreadByRunnable() {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + ":running(实现Runnable接口创建线程)");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 继承Thread类创建线程
     * 1. 继承Thread类,重写run方法
     * 2. 构造Thread的子类
     */
    private static void createThreadByThread() {
        MyThread thread = new MyThread();
        thread.start();
    }

    /**
     * 使用Callable和FutureTask创建线程
     * 1. 实现Callable接口,重写call方法
     * 2. 传入Callable对象,构造FutureTask对象
     * 3. 传入FutureTask对象构造Thread
     */
    private static void createThreadByCallable() throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + ":running(使用Callable创建线程)");
            return "calling";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);

        thread.start();

        String s = futureTask.get();
        System.out.println("使用Callable创建线程的返回值: " + s);
    }

    /**
     * 将Runnable或Callable放到线程池中执行
     * 1. 实现Callable/Runnable接口,重写call/run方法
     * 2. 构建ExecutorService线程池对象,调用线程池execute或者submit方法执行线程
     * 3. 对于submit方式提交,使用Future来获取线程执行结果
     */
    private static void createThreadByPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 1. execute Runnable
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + ":running(通过线程池创建线程:execute Runnable)");
        executorService.execute(runnable);

        // 2. submit Runnable
        Future<?> runnableFuture = executorService.submit(runnable);
        Object o = runnableFuture.get();
        System.out.println("runnableFuture.get() is " + o);

        // 3. submit Callable
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + ":running(通过线程池创建线程:submit Callable)");
            return "submit Callable return msg";
        };
        Future<String> future = executorService.submit(callable);
        String result = future.get();
        System.out.println("result is " + result);

        executorService.shutdown();

    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":running(继承Thread类创建线程)");
    }
}


