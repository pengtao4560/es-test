package com.atguigu.review.blockQueue36;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class MyResource {
    /**
     * 标志位
     * 默认开启 进行生产+消费
     * 多线程环境下不允许用 i++ ++i
     */
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger(); //默认0

    // 阻塞队列的应用。 凡是写架构程序，应该注意通顺，适配。 传接口而非具体的类

    BlockingQueue<String> blockingQueue = null;

    /**
     * 保证程序通用 ：高手都是传接口，而不是传类
     */
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        log.info(blockingQueue.getClass().getName());
    }

    public void myProduce() throws Exception {
        String element = null;
        boolean offered;
        while (FLAG) {
            element = String.valueOf(atomicInteger.incrementAndGet());

            offered = blockingQueue.offer(element, 2L, TimeUnit.SECONDS); // 2秒是为了让我们调试时观察过程

            if (offered) {
                System.out.println(Thread.currentThread().getName() + "\t 元素：" + element + "\t 插入到队列成功");
            } else {
                System.out.println(Thread.currentThread().getName() + element + "\t 插入到队列失败");
            }

            TimeUnit.MILLISECONDS.sleep(500); // 为了让我们调试时观察过程,否则不停歇的一直在循环往队列插入

        }
        System.out.println(Thread.currentThread().getName() + "\t main线程改变了flag状态，叫停资源类的生产操作 flag：" + FLAG);

    }

    public void myConsumer() {
        String result = null;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (result == null || result.equalsIgnoreCase("")) {
                // FIXME
                System.out.println(Thread.currentThread().getName() + "\t main线程改变了flag状态或队列中无元素，叫停资源类的消费操作 flag:" + FLAG);
                System.out.println();
                return;
            } else {
                System.out.println(Thread.currentThread().getName() +  "\t 消费队列中的消息：" + result + " 成功");
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t main线程改变了flag状态，叫停资源类的消费操作操作" + FLAG);

    }

    public void stop() {
        this.FLAG = false;
    }
}

/**
 * volatile/CAS/atomicInteger/原子引用/blockQueue/线程交互
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
                myResource.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "生产者线程").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");

            myResource.myConsumer();
            System.out.println();
        }, "消费者线程").start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
        System.out.println();
        System.out.println("main线程程序叫停， end");
    }
}
