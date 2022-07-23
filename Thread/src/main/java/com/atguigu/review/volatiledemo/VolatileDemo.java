package com.atguigu.review.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData { //MyData.java ===> MyData.class ===> JVM字节码
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性（如果该方法用synchronized 修饰。结果一定是20000.但是太重了(杀鸡用牛刀，高射炮打蚊子)）
    public void addPlusplus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 验证volatile的可见性
 * 1.1假如int number=0, number变量之前根本没有添加volatile.关键字修饰. 也就是没有可见性
 * 2验证olatile不保证原子性
 * 2.1原子性指的是什么意思？
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 * 要么同时成功，要么同时失败。
 * 2.2 volatile 不保证原子性案例演示
 * <p>
 * 2.3 为什么 （数值少于20000， 出现了丢失写值得情况）
 * <p>
 * 2.4 如何解决原子性
 * 1.加sync(高射炮打蚊子）
 * 2.int 用 JUC下的：
 *
 * @see AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) { // main 是一切方法的运行入口
        System.out.println("验证volatile 的不保证原子性(需多次执行查看结果 int type很容易不等于20000)");
        // volatileNotAtomic();

        System.out.println("\n-------------------");
        System.out.println("验证volatile 的保证可见性。 如果 MyData的 number 属性有 volatile 修饰后，则保证可见性，3秒休眠后程序停止。\n" +
                "如果 MyData的 number 属性去掉volatile修饰后，则不保证可见性，3秒休眠后程序不停止");
        seeOkByVolatile();
    }

    /**
     * 验证 volatile 关键字不保证原子性
     */
    private static void volatileNotAtomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++)
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusplus();
                    myData.addAtomic();
                }

            }, String.valueOf("线程" + i)).start();
        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少？
        while (Thread.activeCount() > 2) {  //1 main  2 gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value:" + myData.atomicInteger);
    }

    /**
     * 验证： volatile 关键字可以保证可见性，及时通知其它线程，主物理内存的值已经被修改。
     * 如果没有 volatile 关键字则多线程不保证可见性
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData(); //资源类
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t come in");
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "intput thread name").start();


        // 第2个线程就是我们的main线程
        while (myData.number == 0) {
            //main线程就一直再这里等待循坏，直到 number 的值不再等于零。
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
