# CountDownLatch
CountDownLatch 直译火箭发射倒计时
一句话概括：秦灭六国，一统华夏

## 概念 

让一些线程阻塞直到另一些线程完成一系列操作才被唤醒

CountDownLatch主要有两个方法，当一个或多个线程调用 await() 方法时，调用线程就会被阻塞。其它线程调用 CountDown()方法会
将计数器减1（调用CountDown方法的线程不会被阻塞），当计数器的值变成零时，因调用await方法被阻塞的线程会被唤醒，继续执行

## 场景

现在有这样一个场景，假设一个自习室里有7个人，其中有一个是班长，班长的主要职责就是在其它6个同学走了后，关灯，锁教室门，
然后走人，因此班长是需要最后一个走的，
那么有什么方法能够控制班长这个线程是最后一个执行，而其它线程是随机执行的

## 解决方案

这个时候就用到了CountDownLatch，计数器了。我们一共创建6个线程，然后计数器的值也设置成6

```
// 计数器
CountDownLatch countDownLatch = new CountDownLatch(6);
```

然后每次学生线程执行完，就让计数器的值减1

```
for (int i = 0; i <= 6; i++) {
    new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
        countDownLatch.countDown();
    }, String.valueOf(i)).start();
}
```

最后我们需要通过CountDownLatch的await方法来控制班长主线程的执行，这里 countDownLatch.await()可以想成是一道墙，
只有当计数器的值为0的时候，墙才会消失，主线程才能继续往下执行

```
countDownLatch.await();

System.out.println(Thread.currentThread().getName() + "\t 班长最后关门");
```

不加CountDownLatch的执行结果，我们发现main线程提前已经执行完成了

```
1	 上完自习，离开教室
0	 上完自习，离开教室
main	 班长最后关门
2	 上完自习，离开教室
3	 上完自习，离开教室
4	 上完自习，离开教室
5	 上完自习，离开教室
6	 上完自习，离开教室
```

引入CountDownLatch后的执行结果，我们能够控制住main方法的执行，这样能够保证前提任务的执行

```
0	 上完自习，离开教室
2	 上完自习，离开教室
4	 上完自习，离开教室
1	 上完自习，离开教室
5	 上完自习，离开教室
6	 上完自习，离开教室
3	 上完自习，离开教室
main	 班长最后关门
```
## CountDownLatch 关键方法 等待 await() 计数减少 CountDown()
## CountDownLatch demo

```java
package com.atguigu.review.jucCountdownLaunch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch demo
 * @see com.atguigu.review.jucCountdownLaunch.CountDownLatchDemo
 * @see com.atguigu.review.jucCountdownLaunch.CountDownLatchDemo2
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        mockImplDemandCloseDoorWithBug();

        TimeUnit.SECONDS.sleep(1);

        System.out.println();
        System.out.println("--------以上带bug,以下成功实现---------");
        System.out.println();

        mockImplDemandCloseDoor();
    }

    /**
     * 情景需求：
     * 现在有这样一个场景，假设一个自习室里有7个人，其中有一个是班长，班长的主要职责就是在其它6个同学走了后，关灯，锁教室门，然后走人，因此班长是需要最后一个走的，
     * 那么有什么方法能够控制班长这个线程是最后一个执行，而其它线程是随机执行的
     * {@link ../JUC/7_CountDownLatch_CyclicBarrier_Semaphore使用/CountDownLatch/CountDownLatch.md:11}
     * 执行结果不符合需求
     * 优化
     * @see
     */
    private static void mockImplDemandCloseDoorWithBug() {
        // main 线程模拟班长
//        forithread
        for (int i = 1; i <= 6; i++) {
            int finalI = i;//
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上晚自习，离开教室的是同学" + finalI);

            }, "同学" + i).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门走人");

    }

    private static void mockImplDemandCloseDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // main 线程模拟班长
//        forithread
        for (int i = 1; i <= 6; i++) {
            int finalI = i;//
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上晚自习，离开教室的是同学" + finalI);
                countDownLatch.countDown();
            }, "同学" + i).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门走人");

    }
}

```
demo运行结果：
```
main	 班长最后关门走人
同学2	 上晚自习，离开教室的是同学2
同学1	 上晚自习，离开教室的是同学1
同学3	 上晚自习，离开教室的是同学3
同学4	 上晚自习，离开教室的是同学4
同学5	 上晚自习，离开教室的是同学5
同学6	 上晚自习，离开教室的是同学6

--------以上带bug,以下成功实现---------

同学1	 上晚自习，离开教室的是同学1
同学2	 上晚自习，离开教室的是同学2
同学3	 上晚自习，离开教室的是同学3
同学4	 上晚自习，离开教室的是同学4
同学5	 上晚自习，离开教室的是同学5
同学6	 上晚自习，离开教室的是同学6
main	 班长最后关门走人
```

# CyclicBarrier

## 概念

和 CountDownLatch 相反，一句话概括：集齐七颗龙珠，召唤神龙。也就是做加法，开始是0，加到某个值的时候就执行

CyclicBarrier 的字面意思就是可循环（cyclic）使用的屏障（Barrier）。
CyclicBarrier 要求做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，
所有被屏障拦截的线程才会继续干活，
线程进入屏障通过CyclicBarrier的await() 方法

## 案例

集齐7个龙珠，召唤神龙的Demo，我们需要首先创建CyclicBarrier

```
/**
* 定义一个循环屏障，参数1：需要累加的值，参数2 需要执行的方法
*/
CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
	System.out.println("召唤神龙");
});
```

然后同时编写七个线程，进行龙珠收集，但一个线程收集到了的时候，我们需要让他执行await方法，等待到7个线程全部执行完毕后，我们就执行原来定义好的方法

```
        for (int i = 0; i < 7; i++) {
            final Integer tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到 第" + tempInt + "颗龙珠");

                try {
                    // 先到的被阻塞，等全部线程完成后，才能执行方法
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
```
## CyclicBarrier关键方法 等待 await() 

## CyclicBarrier demo

```java
package com.atguigu.review.jucCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：可循环使用Cyclic的屏障 Barrier
 * @see com.atguigu.review.jucCyclicBarrier.CyclicBarrierDemo;
 * @see java.util.concurrent.CyclicBarrier
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

```

demo运行结果：
```
1	 收集到龙珠：1
2	 收集到龙珠：2
3	 收集到龙珠：3
4	 收集到龙珠：4
5	 收集到龙珠：5
6	 收集到龙珠：6
7	 收集到龙珠：7
7	 以上7龙珠收集齐召唤神龙

```

# Semaphore：信号量

## 概念

信号量主要用于两个目的

- 一个是用于共享资源的互斥使用
- 另一个用于并发线程数的控制

## 案例

我们模拟一个抢车位的场景，假设一共有6个车，3个停车位

那么我们首先需要定义信号量为3，也就是3个停车位

```
/**
* 初始化一个信号量为3，默认是false 非公平锁， 模拟3个停车位
*/
Semaphore semaphore = new Semaphore(3, false);
```

然后我们模拟6辆车同时并发抢占停车位，但第一个车辆抢占到停车位后，信号量需要减1

```
// 代表一辆车，已经占用了该车位
semaphore.acquire(); // 抢占
```

同时车辆假设需要等待3秒后，释放信号量

```
// 每个车停3秒
try {
	TimeUnit.SECONDS.sleep(3);
} catch (InterruptedException e) {
	e.printStackTrace();
}
```

最后车辆离开，释放信号量

```
// 释放停车位
semaphore.release();
```
## Semaphore 关键方法：得到 acquire() 释放 release()  
## Semaphore demo

```java
package com.atguigu.review.jucSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯 信号量 Semaphore
 * @see com.atguigu.review.jucSemaphore.SemaphoreDemo
 * @see java.util.concurrent.Semaphore
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

```
运行结果

```
1	 抢占车位成功：1
3	 抢占车位成功：3
2	 抢占车位成功：2
3	 使用车位结束，离开车位 ：3
2	 使用车位结束，离开车位 ：2
1	 使用车位结束，离开车位 ：1
5	 抢占车位成功：5
4	 抢占车位成功：4
6	 抢占车位成功：6
5	 使用车位结束，离开车位 ：5
4	 使用车位结束，离开车位 ：4
6	 使用车位结束，离开车位 ：6

Process finished with exit code 0

```

看运行结果能够发现，1 3 2 车辆首先抢占到了停车位，然后等待2秒后，离开，然后后面 5 4 6 又抢到了车位



