package com.atguigu.review.oom81;

import java.util.Random;

/**
 *
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        String str = "pengtao";

/*        while (true) {
            str += str + new Random().nextInt(111111111) + new Random().nextInt(22222222);
        }*/
        byte[] bytes = new byte[80 * 1024 * 1024];
        // VM 启动参数配置  -Xms10m -Xmx10m

    }
}
