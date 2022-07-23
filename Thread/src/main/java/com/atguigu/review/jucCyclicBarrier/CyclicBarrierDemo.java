package com.atguigu.review.jucCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：可循环使用Cyclic的屏障 Barrier
 * @see com.atguigu.review.jucCyclicBarrier.CyclicBarrierDemo;
 * @see CyclicBarrier
 * CyclicBarrier 要求做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活，
 * 线程进入屏障通过CyclicBarrier的await() 方法
 * 一句话概括：集齐七颗龙珠召唤神龙
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{

           System.out.println(Thread.currentThread().getName() + "\t 以上7龙珠收集齐召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            int finalI = i;//
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +"\t 收集到龙珠：" + finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
