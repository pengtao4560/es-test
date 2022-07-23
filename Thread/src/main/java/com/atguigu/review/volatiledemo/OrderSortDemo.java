package com.atguigu.review.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * java 指令重排 demo演示
 */
public class OrderSortDemo {

    public static int x = 0;
    public static int y = 0;
    public static int a = 0;
    public static int b = 0;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            // try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }

            reSort();
        }
    }

    private static void reSort() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            a = 1; // 操作1
            x = b; // 操作2
        }, "t1");
        Thread t2 = new Thread(() -> {
            b = 1; // 操作3
            y = a; // 操作4
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        if (x == 0 && y == 0) {
            System.out.println("=================== 程序异常输出： (" + x + "," + y + ")");
        } else {
            // System.out.println("程序正常输出： (" + x + "," + y + ")");
        }
        x = 0;
        y = 0;
        a = 0;
        b = 0;

    }
}
