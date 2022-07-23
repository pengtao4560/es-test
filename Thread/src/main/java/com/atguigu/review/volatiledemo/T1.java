package com.atguigu.review.volatiledemo;

/**
 * 查看 i++ 字节码
 */
public class T1 {
    volatile int n = 0;

    /**n++被拆分成了3个指令：
     执行getfield:拿到原始n;
     执行iadd进行加1操作：
     执行outfield写把累加后的值写回
     具体需要 javap-c 查看字节码汇编 TODO
     */
    public void add() {
        n++;
    }
}
