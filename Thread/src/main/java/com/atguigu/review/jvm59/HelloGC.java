package com.atguigu.review.jvm59;

/**
 * 用该demo执行之后
 * 切入  jvm 参数
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        int setMetaspaceSize = 1073741824 / 1024 / 1024;
        System.out.println(268435456 / 1024 / 1024);
        System.out.println( 4263510016L / 1024 / 1024);
        System.out.println(setMetaspaceSize);

        System.out.println("hello GC");
//        byte[] bytes = new byte[50 * 1024 * 1024];
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
