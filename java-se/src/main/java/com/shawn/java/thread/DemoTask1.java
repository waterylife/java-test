package com.shawn.java.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Shawn on 2017/6/19.
 */
public class DemoTask1 implements Callable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTask1.class);

    public List<Integer> call() throws Exception {
        LOGGER.info("DemoTask1 Call Start", Thread.currentThread().getName());
        Thread.sleep(4);
        LOGGER.info("DemoTask1 Call End", Thread.currentThread().getName());

        List<Integer> result = new ArrayList<Integer>();
        result.add(1);
        result.add(2);
        return result;
    }




}
