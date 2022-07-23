package com.atguigu.review.jucSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯 信号量 Semaphore
 * @see com.atguigu.review.jucSemaphore.SemaphoreDemo
 * @see Semaphore
 * 一句话概括：抢车位
 */
public class  SemaphoreDemo {

    public static void main(String[] args) {

        // 模
        /**
         * 初始化一个信号量为3，默认是false 非公平锁，
         * 拟一个三个车位的情况，6辆车进行抢车位。   车位即 资源  车即线程
         */
        Semaphore semaphore = new Semaphore(3, false);
        /* 模拟6辆车 */
        for (int i = 1; i <= 6; i++) {
            int finalI = i;//
            new Thread(() -> {
                // System.out.println(Thread.currentThread().getName() +"\t 开始抢占车位：" + finalI);
                try {
                    semaphore.acquire();
                    // 抢占到车位的日志必须在 acquire()方法之后打印，日志才有效
                    System.out.println(Thread.currentThread().getName() +"\t 抢占车位成功：" + finalI);

                    // 模拟使用车位 3 秒钟
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 离开车位的日志必须在 release()方法之前打印，日志才有效
                    System.out.println(Thread.currentThread().getName() +"\t 使用车位结束，离开车位 ：" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
