package com.atguigu.review.blockQueue36;



//import org.testng.annotations.Test;



import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * @see com.atguigu.review.blockQueue36.ArrayBlockQueueDemo
 */
public class ArrayBlockQueueDemo {

    @Test
    public void testAddAndRemoveAnd(){
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        addAndRemoveAnd(arrayBlockingQueue);
    }

    @Test
    public void testOfferAndPoll(){
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        offerAndPoll(arrayBlockingQueue);
    }

    @Test
    public void testPutAndTake() throws InterruptedException {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        putAndTake(arrayBlockingQueue);
    }

    @Test
    public void testOfferAndPollLimitTime() throws InterruptedException {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        offerAndPollLimitTime(arrayBlockingQueue);
    }

    private void offerAndPollLimitTime(BlockingQueue<String> arrayBlockingQueue) throws InterruptedException {
        System.out.println(arrayBlockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("x", 2L, TimeUnit.SECONDS)); // 限时 2秒插入不成功就放弃了
    }


    /**
     * BlockQueue 阻塞方法
     * @see ArrayBlockingQueue#put(Object)
     * @see ArrayBlockingQueue#take()
     * * 官网推荐方法
     * @param arrayBlockingQueue
     * @throws InterruptedException
     */
    private static void putAndTake(BlockingQueue<String> arrayBlockingQueue) throws InterruptedException {
        System.out.println("测试阻塞队列的 put()/ take() 之 put()");

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        // arrayBlockingQueue.put("d"); // main线程挂起，直到等待 “d” 元素 被放进去

        System.out.println();
        System.out.println("测试阻塞队列的 put()/ take() 之 take()");
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take()); // main线程挂起，直到等待 下一个元素 被取出来

    }

    /**
     * @see ArrayBlockingQueue#offer(Object)
     * @see ArrayBlockingQueue#poll()
     * @see ArrayBlockingQueue#peek()
     * @param arrayBlockingQueue
     */
    private static void offerAndPoll(BlockingQueue<String> arrayBlockingQueue) {
        System.out.println("测试阻塞队列的 offer()/ take() 之 put()");
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("x")); // false

        System.out.println(arrayBlockingQueue.peek());
        System.out.println("测试阻塞队列的 offer()/ take() 之 poll()");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll()); // null

    }


    /**
     * add() remove() 抛出异常组
     *
     * 当阻塞队列满时，再往队列中add插入元素会抛出IllegalStateException：Queue full
     *             当阻塞队列空时，再在队列中remove移除元素会抛NoSuchElementException
     * @see ArrayBlockingQueue#add(Object)
     * @see ArrayBlockingQueue#remove()
     * @see ArrayBlockingQueue#element()
     * @param arrayBlockingQueue
     */
    private static void addAndRemoveAnd(BlockingQueue<String> arrayBlockingQueue) {
        System.out.println("测试阻塞队列的 add()/ remove() 之 add()");
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        // arrayBlockingQueue.add("x"); // java.lang.IllegalStateException: Queue full

        System.out.println();
        System.out.println("测试阻塞队列的 add()/ remove() 之 remove()");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        // System.out.println(arrayBlockingQueue.remove()); // java.util.NoSuchElementException
        /*
         * 结论：阻塞队列之抛出异常组
         */
        System.out.println(arrayBlockingQueue.remainingCapacity());
    }
}
