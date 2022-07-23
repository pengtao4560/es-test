package com.atguigu.review.deadlock;

/**
 * 死锁条件
 * 互斥使用：一个资源只能分配给一个线程
 * 不可剥夺：资源只能由占有者释放，申请者不能强制剥夺
 * 请求保持：线程申请资源时，保持对原有资源的占有
 * 循环等待：存在一个进程等待队列：{P1 , P2 , … , Pn}, 其中P1等待P2占有的资源，P2等待P3占有的资源，…，Pn等待P1占有的资源，形成一个进程等待环路
 *
 * 代码
 * 思路
 * 定义两个资源o1，o2
 * 对象deadLock1占有资源o1，需要资源o2
 * 对象deadLock2占有资源o2，需要资源o1
 * 死锁产生
 *
 * @author Administrator
 */
public class DeadLock implements Runnable {
    /**
     * 定义两个Object对象，模拟两个线程占有的共享资源
     * 此处需要注意的是，o1和o2 需要有static修饰，定义为静态对象，这样o1和o2才能在多个线程之间调用，才属于共享资源，
     * 没有static修饰的话，DeadLock的每个实例对象中的 o1和o2 都将是独立存在，相互隔离的，
     */
    public static Object o1 = new Object();
    public static Object o2 = new Object();

    public int flag; // 属性，又叫成员变量

    public DeadLock(int flag) {
        super();
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 1) {
            // 代码块1
            synchronized (o1) {
                System.out.println("one-1");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("one-2");
                }
            }
        } else {
            // 代码块2
            synchronized (o2) {
                System.out.println("two-1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("two-2");
                }
            }
        }
    }

    public static void main(String[] args) {
        //创建线程1，flag 属性值为1
        DeadLock deadLock1 = new DeadLock(1);
        //创建线程1，flag 属性值为2
        DeadLock deadLock2 = new DeadLock(2);
        //启动线程1和线程2
        /**
         * 线程1启动之后，调用顺序是
         * （1）执行代码块1，同时获取到o1对象锁，开始执行，线程沉睡1秒
         * （2）接着去获取o2的对象锁，由于第二个线程先获取的是o2的对象锁，所以需要等待代码块2执行完毕，才能获取到o2的对象锁
         */
        new Thread(deadLock1).start();
        /**
         * 线程2启动之后，调用顺序是
         * （1）执行代码块2，同时获取到o2对象锁，开始执行，线程沉睡1秒
         * （2）接着去获取o1的对象锁，由于第一个线程先获取的是o1的对象锁，所以需要等待代码块1执行完毕，才能获取到o1的对象锁
         */
        new Thread(deadLock2).start();
        /** 以上分析可得，线程一和线程二共用了对象o1和o2，各自都想要获取对方的锁，从而形成阻塞，一直等待下去，这种现象就是死锁。*/

    }

}
