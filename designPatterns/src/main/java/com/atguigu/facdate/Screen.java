package com.atguigu.facdate;

/**
 * 外观模式- 屏幕
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Screen {

    // 使用 饿汉式单例模式
    private static Screen INSTANCE = new Screen();

    private Screen() {
    }

    public static Screen getInstance() {
        return INSTANCE;
    }

    public void up() {
        System.out.println(" Screen up ");
    }

    public void down() {
        System.out.println(" Screen down ");
    }

}
