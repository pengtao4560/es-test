package com.atguigu.review.threadpool46;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 第四种 获得、使用java多线程的方式： 线程池。  前三种： 继承Thread类/ 实现Runable接口/实现Callable接口
 */
public class MyThreadPoolDemo {

    /**
     * @see java.util.concurrent.Executor
     * @see Executors
     * @see ExecutorService
     */
    public static void main(String[] args) {
        int nThreads = 5;
        // 执行长期任务性能好很多
        System.out.println();
        ExecutorService threadPool =  Executors.newFixedThreadPool(nThreads);
        testThreadPool(threadPool);

        System.out.println();
        // 一个任务一个任务执行的场景
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        testThreadPool(threadPool2);

        System.out.println();
        // 适用：执行很多短期异步的小程序或者负载较轻的服务器
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor(); // 一池一个处理线程
        testThreadPool(threadPool3);
    }


    /**
     * @see Executors#newFixedThreadPool(int)
     */
    @Test
    public void testFixedHtreadPool() {
        // 一个线程池（银行）5个处理线程（银行5个柜员)
        int nThreads = 5;
        testThreadPool(Executors.newFixedThreadPool(nThreads));
    }

    /**
     * @see Executors#newCachedThreadPool()
     */
    @Test
    public void testCacheedTreadPool() {
        // 一池N个处理线程。 根据业务数量N不定 ，根据用户(业务线程数量)安排柜员(处理线程CachedThread)加班
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        testThreadPool(threadPool2);
    }

    /**
     * @see Executors#newSingleThreadExecutor()
     */
    @Test
    public void testSingleTreadPool() {
        // 一池一个处理线程。 周末就一个柜员(处理线程)值班
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        testThreadPool(threadPool);
    }

    private static void testThreadPool(ExecutorService threadPool) {
        try {

            for (int i = 1; i <= 10; i++) { // 不管多少个用户。处理线程只能有 nThreads 个

                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");

                });
                // 暂停一会线程
          /*      try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
