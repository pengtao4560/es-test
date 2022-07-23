package com.atguigu.review.oom81;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Direct buffer memory
 */
public class DirectBufferMemory {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory(默认是电脑内存的1/4) 是：");
        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024 + "MB");
        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024 / 1024 + "G");

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        /*  -XX:MaxDirectMemorySize=5m 我们配置JM参数的 MaxDirectMemorySize 为 5m, 又占用了6m，故运行后出现了
        Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory*/

        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
