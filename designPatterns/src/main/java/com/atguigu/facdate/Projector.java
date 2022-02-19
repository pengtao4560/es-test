package com.atguigu.facdate;

/**
 * 外观模式- 投影仪
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Projector {

    // 使用 饿汉式单例模式
    private static Projector INSTANCE = new Projector();

    private Projector() {
    }

    public static Projector getInstance() {
        return INSTANCE;
    }

    public void on() {
        System.out.println(" Projector on ");
    }
    public void off() {
        System.out.println(" Projector off ");
    }

    public void focus() {
        System.out.println(" Projector is focus ");
    }

}
