package com.atguigu.review.threadpool46;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手写线程池
 */
public class MyThreadPool {

    public static void main(String[] args) {
        /**
         * 返回java虚拟机可用的 处理器 processors 数量    cpu: central processing unit
         * 4/1-0.9 = 40
         */
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("java虚拟机可用的 处理器数量：" + processors);
        System.out.println();

        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 2L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        System.out.println("1 号 和 2 号窗口是核心线程,  核心线程数是" + corePoolSize +" 最大线程数是" + maximumPoolSize);
        System.out.println();

        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        try {
            for (int i = 1; i <= 10; i++) {
                final int finalI = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 号窗口， 服务顾客（模拟线程编号为：）" + finalI);
                    try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
