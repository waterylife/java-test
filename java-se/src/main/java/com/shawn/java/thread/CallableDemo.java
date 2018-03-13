package com.shawn.java.thread;

import java.util.concurrent.Callable;

/**
 * Created by Shawn on 2017/4/17.
 */
public class CallableDemo implements Callable {
    private ThreadLocal<Long> count = new ThreadLocal<Long>();


    public Object call() throws Exception {
        Long currentCount = 0L;

        count.set(currentCount);
        for(int i = 0; i < 1000; i ++) {
            currentCount = count.get();
            count.set(++currentCount);
        }

        return count.get();
    }
}
