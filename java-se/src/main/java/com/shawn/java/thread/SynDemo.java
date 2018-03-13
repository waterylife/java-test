package com.shawn.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Shawn on 2017/7/12.
 */
public class SynDemo implements Callable {
//    private static volatile int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void increaseCount() {
//        count++;
        count.getAndIncrement();
    }


    /*public static int getCount() {
        return count;
    }*/

    public AtomicInteger call()
            throws Exception {
        for(int i = 0; i < 100; i++) {
            increaseCount();
        }
        return count;
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for(int i = 0; i < 20; i++) {
            futureList.add(threadPoolExecutor.submit(new SynDemo()));
        }

        try {
            for (Future<Integer> future : futureList) {
                future.get();
            }
        } catch (ExecutionException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(count);


    }
}
