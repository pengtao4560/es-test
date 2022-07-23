package com.atguigu.review.volatiledemo;

/**
 * 多线程下指令重排测试
 */
public class ResortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        try {
            // TimeUnit.SECONDS.sleep(1);
            a = 1; //语句1
            flag = true; //语句2
            System.out.println("retValue: " + a);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程环境中线程交替执行，由于编译器优化重排的存在，
     * 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
     */
    public void method02() {
        if (flag) {
            a = a + 5; //语句3
            System.out.println("retValue: " + a);
        }
    }
    // 验证：14行代码 注释与否 模拟多线程下的情况。
    public static void main(String[] args) {

            ResortSeqDemo resortSeq = new ResortSeqDemo();

            new Thread(()->{
                resortSeq.method01();
            },"ThreadA").start();


            new Thread(()->{
                resortSeq.method02();
            },"ThreadB").start();

    }
}
