package com.atguigu.review.cas;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS demo
 */
public class CASDemo {
    public static AtomicInteger atomicInteger = new AtomicInteger(5);

    public static void main(String[] args) {
        // main do thing...

        /**
         * @see AtomicInteger#compareAndSet(int, int)
         * @see Unsafe#compareAndSwapInt(Object, long, int, int)
         */
/*        boolean b = atomicInteger.compareAndSet(5, 2022); // 比较并交换
        System.out.println(b + "\t current data:" + atomicInteger.get());

        boolean b2 = atomicInteger.compareAndSet(5, 1024);
        System.out.println(b2 + "\t current data:" + atomicInteger.get());

        atomicInteger.getAndIncrement();*/

        while (true) {
            casDemo();
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }

            atomicInteger.set(5);
            System.out.println("");
        }
    }

    private static void casDemo() {
        new Thread(() -> {
            boolean b =  atomicInteger.compareAndSet(5, 2022);
            System.out.println(Thread.currentThread().getName() + "\t t1线程执行");
            System.out.println(b + "\t current data:" + atomicInteger.get());

        }, "t1").start();
        new Thread(() -> {
            boolean b = atomicInteger.compareAndSet(5, 1024);
            System.out.println(Thread.currentThread().getName() + "\t t2线程执行");
            System.out.println(b + "\t current data:" + atomicInteger.get());

        }, "t2").start();
    }
}
