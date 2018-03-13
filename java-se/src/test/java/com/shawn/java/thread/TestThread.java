package com.shawn.java.thread;

import org.junit.Test;

import java.util.concurrent.FutureTask;

/**
 * Created by Shawn on 16/3/8.
 */
public class TestThread {
    @Test
    public void TestRunnableImpl() throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    public void TestCallable() throws Exception {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask futureTask = new FutureTask(callableDemo);

        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);

        thread1.start();
//        thread2.start();

        Long result = (Long)futureTask.get();
        System.out.println(result);
    }

}
