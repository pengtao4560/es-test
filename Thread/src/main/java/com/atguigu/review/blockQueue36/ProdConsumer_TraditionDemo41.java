package com.atguigu.review.blockQueue36;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1. 判断  （多线程的判断必须用while，否则可运行看结果：控制不住）
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }

            // 2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 3. 通知唤醒
            condition.signal();

        } finally {
            // 写锁 释放
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1. 判断
            while (number == 0) {
                // 等待，不能生产
                condition.await();
            }

            // 2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 3. 通知唤醒
            condition.signal();

        } finally {
            // 写锁 释放
            lock.unlock();
        }
    }

}

/**
 * P41 线程通信之生产者消费者传统版
 * <p>
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减一，来5轮
 * 类比： 一个空调（资源类） 两个同学对空调交替操作，一同学需要升高一度  另一个同学要降温1度。      升温降温的方法是在资源类自身就携带：出场模式时空调就有制冷制热功能
 * <p>
 * 多线程的企业级模板口诀
 * 高并发 高内聚(资源类里自带操作方法  空调自带制冷制热) 低耦合
 * <p>
 * - 线程   操作（方法）     资源类
 * - 判断   干活      通知
 * <p>
 * - 严防多线程并发下的虚假唤醒   多线程的判断一定用while 而不是 if
 * 多线程的操作东西一定要有一个资源类
 * Synchronized 被
 * @see Lock 替代
 * wait() 方法 被 await()方法替代
 * notify() 方法 被 signal() 替代
 * wait()/notify() 方法属于Object类
 * @see Object#wait()
 *
 * wait方法的注释截取：
 * As in the one argument version, interrupts and spurious wakeups are possible, and this method should always be used in a loop:
 * 在一个参数版本中，中断和虚假唤醒是可能发生的，所以这个方法应该总是在循环中使用:
 */
public class ProdConsumer_TraditionDemo41 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();


        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;//
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;//
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();


        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;//
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程C").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;//
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程D").start();

    }


}
