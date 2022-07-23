package review.reentrantLock;

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取到该锁的代码，在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也就是说：`线程可以进入任何一个它已经拥有的锁所同步的代码块`
 * ReentrantLock/synchronized 是典型的可重入锁
 */

import java.util.concurrent.TimeUnit;

/**
 * 资源类
 */
class Phone {

    /**
     * 发送短信
     * @throws Exception
     */
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        // 在同步方法中，调用另外一个同步方法
        sendEmail();
    }

    /**
     * 发邮件
     */
    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail()");
    }
}

public class SynchronizedIsReentrantLockDemo {

    public static void main(String[] args) {
        synchronizedIsRenentrantLock();
    }

    /**
     * 证明 synchronized 是典型的可重入锁
     */
    private static void synchronizedIsRenentrantLock() {
        Phone phone = new Phone();

        // 两个线程操作资源类
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
