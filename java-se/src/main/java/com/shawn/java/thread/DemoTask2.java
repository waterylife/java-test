package com.shawn.java.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;

/**
 * Created by Shawn on 2017/6/22.
 */
public class DemoTask2 implements Callable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTask2.class);

    public String call() throws Exception {
        LOGGER.info("DemoTask2 Call Start", Thread.currentThread().getName());
        Thread.sleep(3);
        LOGGER.info("DemoTask2 Call End", Thread.currentThread().getName());

        return "Demotask2";
    }

}
