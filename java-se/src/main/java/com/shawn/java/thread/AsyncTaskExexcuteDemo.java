package com.shawn.java.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Shawn on 2017/6/19.
 */
public class AsyncTaskExexcuteDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskExexcuteDemo.class);

    private ThreadPoolExecutor threadPoolExecutor;

    private DemoTask1 demoTask1;

    private DemoTask2 demoTask2;

    private long executeTime;

    private List<AsyncCall> asyncCallList;

    public AsyncTaskExexcuteDemo() {
        threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new DemoTheadFactory());

        asyncCallList = new ArrayList<AsyncCall>();
    }


    public void executeAsyncTask() {
        //任务提交到线程池
        AsyncCall asyncCall1 = new AsyncCall(threadPoolExecutor.submit(demoTask1),
                new Processor<List<Integer>>() {
                    public void process(List<Integer> result) {
                        LOGGER.info("result from Task1: " + result);
                    }
                });
        asyncCallList.add(asyncCall1);
        AsyncCall asyncCall2 = new AsyncCall(threadPoolExecutor.submit(demoTask2),
                new Processor<String>() {
                    public void process(String result) {
                        LOGGER.info("result from Task2: " + result);
                    }
                });
        asyncCallList.add(asyncCall2);

        //获取任务执行结果
        long leftTime = executeTime;
        long startTime = 0;
        int resultCode = 0;
        for(AsyncCall asyncCall : asyncCallList) {
            if(leftTime <= 0) { resultCode = -1; break;}
            startTime = System.currentTimeMillis();
            if(-1 == asyncCall.get(leftTime)) {break;}
            leftTime = System.currentTimeMillis() - startTime;
        }

        if(resultCode == -1) {
            LOGGER.error("Timeout");
        }

    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public void setDemoTask1(DemoTask1 demoTask1) {
        this.demoTask1 = demoTask1;
    }

    public void setDemoTask2(DemoTask2 demoTask2) {
        this.demoTask2 = demoTask2;
    }

    static class DemoTheadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DemoTheadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "demoPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public interface Processor<T> {
        public void process(T t);
    }


    public class AsyncCall {
        private Future<?> future;

        private Processor processor;

        public AsyncCall(Future future, Processor processor) {
            this.future = future;
            this.processor = processor;
        }

        public int get(long leftTime) {
            if(leftTime <= 0) return -1;

            try {
                Object result = future.get(leftTime, TimeUnit.MILLISECONDS);
                processor.process(result);
            } catch (Exception e) {
                future.cancel(true);
                LOGGER.error("", e);
                return -1;
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        AsyncTaskExexcuteDemo asyncTaskExexcuteDemo = new AsyncTaskExexcuteDemo();
        asyncTaskExexcuteDemo.setExecuteTime(10);

        asyncTaskExexcuteDemo.setDemoTask1(new DemoTask1());
        asyncTaskExexcuteDemo.setDemoTask2(new DemoTask2());

        asyncTaskExexcuteDemo.executeAsyncTask();

        return;
    }
}

