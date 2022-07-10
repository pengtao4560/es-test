package Thread;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存一致性协议demo
 * 缓存行是64个字节，
 */
public class CacheLineAdding {

    public static long COUNT = 10_0000_0000L;


    private static class T {
        // private long p1,p2,p3,p4,p5,p6,p7; //8个字节 * 7个 56个字节加上 x的8个字节刚好64个字节时候一个缓存行。
        private long x = 0L; // 8 byte;
        // private long p11,p12,p13,p14,p15,p16,p17;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        }, "t1");


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }

            latch.countDown();
        }, "t2");

        long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }

}
