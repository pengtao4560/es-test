package com.atguigu.review.threadpool46;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 */
class MyThread implements Runnable {
    @Override
    public void run() {

    }
}

/**
 * @see Callable
 */
class CallableImpl implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("**进入方法 CallableImpl#call() **");
        System.out.println(Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

/**
 * 多线程中，获取多线程的方式
 * @see FutureTask 直译将来的任务
 * @see FutureTask#FutureTask(Callable)
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread();
        t1.start();

        // Thread(Runnable target, String name) // AlLocates a new Thread object. 分配一个新的Thread对象

        FutureTask<Integer> futureTask = new FutureTask<>(new CallableImpl());
        new Thread(futureTask, "线程t1").start();
        // 如果用的是同一个futureTask 对象。就会得到同一个结果，所以不会进入两遍call()方法，只会进入一遍，线程t2不调用。想要调用2次。则new新的futureTask2
        new Thread(futureTask, "线程t2").start(); //线程t2启动了，但是futureTask的call()方法未进入，而是复用了 51行的 futureTask#call()


        System.out.println(Thread.currentThread().getName() + "\t ******");

        int result01 = 100;
        while (!futureTask.isDone()) {

        }
        Integer result02 = futureTask.get(); // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，值得计算完成
        System.out.println("最终需要的结果：" + (result01 + result02));


        // 为什么会出现Callable： 因为并发、异步催生 Callable出现  千次接口再一批次调用

    }
}

