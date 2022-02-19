package com.atguigu.facdate;

/**
 * 外观模式- 立体声机
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Stereo {

    // 使用 饿汉式单例模式
    private static Stereo INSTANCE = new Stereo();

    private Stereo() {
    }

    public static Stereo getInstance() {
        return INSTANCE;
    }

    public void on() {
        System.out.println(" Screen on ");
    }

    public void off() {
        System.out.println(" Screen off ");
    }

    public void up() {
        System.out.println(" Screen voice up... ");
    }

}
