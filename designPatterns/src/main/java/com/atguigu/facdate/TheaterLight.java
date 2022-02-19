package com.atguigu.facdate;

/**
 * 外观模式- 影院灯光
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class TheaterLight {

    // 使用 饿汉式单例模式
    private static TheaterLight INSTANCE = new TheaterLight();

    private TheaterLight() {
    }

    public static TheaterLight getInstance() {
        return INSTANCE;
    }

    public void on() {
        System.out.println(" TheaterLight on ");
    }
    public void off() {
        System.out.println(" TheaterLight off ");
    }

    public void dim() {
        System.out.println(" TheaterLight  dim ");
    }

    //....
    public void bright() {
        System.out.println(" TheaterLight bright ..");
    }

}
