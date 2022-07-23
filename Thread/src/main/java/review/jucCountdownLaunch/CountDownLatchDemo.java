package review.jucCountdownLaunch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
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
