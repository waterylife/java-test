package com.shawn.java.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shawn on 16/3/8.
 * Runnable接口实现示例
 */
public class RunnableImpl implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnableImpl.class);

    public void run() {
//        LOGGER.info("run, theadid: ", Thread.currentThread().getId());
        System.out.println("run, theadid: " + Thread.currentThread().getId());
    }
}
