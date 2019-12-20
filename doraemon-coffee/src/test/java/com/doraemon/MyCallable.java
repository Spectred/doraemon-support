package com.doraemon;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class MyCallable implements Callable<Integer> {

    private CountDownLatch cdl;


    private Integer i;


    @Override
    public Integer call() throws Exception {
        try {
            Thread.sleep(1000);
        } finally {
            cdl.countDown();
        }
        return i + 1;
    }


}
