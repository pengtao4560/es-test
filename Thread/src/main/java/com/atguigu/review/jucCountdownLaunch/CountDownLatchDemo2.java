package com.atguigu.review.jucCountdownLaunch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {

        mockImplFinishBigJob();
    }

    /**
     * 模拟 秦灭六国
     * @throws InterruptedException
     */
    private static void mockImplFinishBigJob() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // main 线程模拟班长
//        forithread
        for (int i = 1; i <= 6; i++) {
            int finalI = i;//
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +"\t 秦国消灭了" + CountryEnum.foreachCountryEnum(finalI).getName());
                countDownLatch.countDown();
            }, CountryEnum.foreachCountryEnum(finalI).getName()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦灭六国一统华夏");

    }
}
