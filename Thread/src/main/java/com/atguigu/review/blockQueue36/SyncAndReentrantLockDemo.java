package com.atguigu.review.blockQueue36;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 lock 有什么区别？用新的lock有什么好处？举例说明
 * <p>
 * 第五点： 锁绑定多个条件 Condition
 * synchronized没有 Condition这种说法
 * ReentrantLock,用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized.要么随机唤醒一个线程要么唤醒全部线程。
 * <p>
 * 题目：多线程之间按顺序调用，实现 A->B->C->A 三个线程启动，要求如下：
 * AA打3次，BB打4次，CC打5次
 * 紧接着
 * AA打3次，BB打4次，CC打5次
 * 来10轮
 */

/**
 * 线程操作的资源类
 */
class ShareResource {

    private int number = 1; //A:1 B:2 C:3

    private Lock lock = new ReentrantLock();
    /* 一把锁 3把钥匙 */
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print3()  {
        lock.lock();
        try {
            //1. 判断
            while (number != 1) {
                condition1.await();
            }

            // 2.干活
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 循环i：" + i);
            }

            // 3.通知 通知前修改 状态符
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print4()  {
        lock.lock();
        try {
            //1. 判断
            while (number != 2) {
                condition2.await();
            }

            // 2.干活
            for (int i = 1; i <= 4; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 循环i：" + i);
            }

            // 3.通知
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print5() {
        lock.lock();
        try {
            //1. 判断
            while (number != 3) {
                condition3.await();
            }

            // 2.干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 循环i：" + i);
            }

            // 3.通知
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print3();

            }
        }, "T1").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print4();

            }
        }, "T2").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();

            }
        }, "T3").start();
    }
}
