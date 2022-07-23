package com.atguigu.review.volatiledemo;

/**
 * 单例模式
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法singletonDemo() ");
    }

    /*    public static SingletonDemo getInstance() {
            if (instance == null) {
                instance = new SingletonDemo();
            }
            return instance;
        }*/

    // DCL(Doouble check Lock双端检锁机制)
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;

    }

    //

    public static void main(String[] args) {
        /* 单线程（main线程的操作动作。。。。。。）
         */
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance()); //引用类型 == 比较内存地址
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        // 并发多线程后，情况发生了很大的变化

        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
        // 多线程下控制台打印结果是不确定的
    }

    /**
     * main	 我是构造方法singletonDemo()
     * true
     * true
     * true
     */
}
