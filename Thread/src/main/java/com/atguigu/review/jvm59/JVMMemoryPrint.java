package com.atguigu.review.jvm59;

/**
 * 打印 Java虚拟机中内存的总量
 * 以及Java虚拟机中试图使用的最大内存量
 */
public class JVMMemoryPrint {
    public static void main(String[] args) throws InterruptedException {
        // 返回Java虚拟机中内存的总量
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 返回Java虚拟机中试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("初始堆内存大小大概是内存大小的 1/64");
        System.out.println((totalMemory / (double) 1024 / 1024) * 64 / 1024 + "G");

        System.out.println("------------");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("初始堆内存大小大概是内存大小的 1/4");
        System.out.println((maxMemory / (double) 1024 / 1024) * 4 / 1024 + "G");

    }
}
