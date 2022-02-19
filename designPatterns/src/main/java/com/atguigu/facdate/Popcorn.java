package com.atguigu.facdate;

/**
 * 爆米花机
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Popcorn {

    // 使用 饿汉式单例模式
    private static Popcorn INSTANCE = new Popcorn();

    private Popcorn() {
    }

    public static Popcorn getInstance() {
        return INSTANCE;
    }

    public void on() {
        System.out.println(" Popcorn on ");
    }
    public void off() {
        System.out.println(" Popcorn off ");
    }

    /** 爆米花机爆米花 */
    public void pop() {
        System.out.println(" Popcorn is poping ");
    }

}
