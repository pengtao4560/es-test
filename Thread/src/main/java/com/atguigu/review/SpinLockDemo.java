package com.atguigu.review;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写一个自旋锁
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，B随后进来发现当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随后抢到
 * * 循环比较获取直到成功为止，没有类似于wait的阻塞
 */
public class SpinLockDemo {

    // 现在的泛型装的是Thread，原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        // 获取当前进来的线程
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t invoked myLock()");

        // 开始自旋，期望值是null，更新值是当前线程，如果是null，则更新为当前线程，否者自旋
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    /**
     * 解锁
     */
    public void myUnLock() {

        // 获取当前进来的线程
        Thread thread = Thread.currentThread();

        // 自己用完了后，把atomicReference变成null
        atomicReference.compareAndSet(thread, null);

        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {

        testMySpinLock();

    }

    /**
     * 测试手写的自旋锁 demo
     */
    private static void testMySpinLock() {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        /* 启动t1线程，开始占用锁以及释放锁 */
        new Thread(() -> {
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnLock();
            }


        }, "线程AAA").start();

        /* 让main线程暂停1秒，使得 线程AAA ，先执行 */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* 启动 线程BBB ，开始占用锁以及释放锁 */
        new Thread(() -> {
            try {
                spinLockDemo.myLock();
            } finally {
                spinLockDemo.myUnLock();
            }

        }, "线程BBB").start();
    }

}
