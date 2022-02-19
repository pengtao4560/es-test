package com.atguigu.facdate;

/**
 * 外观模式- DVD播放器
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class DVDPlayer {

    // 使用 饿汉式单例模式
    private static DVDPlayer INSTANCE = new DVDPlayer();

    private DVDPlayer() {
    }

    public static DVDPlayer getInstance() {
        return INSTANCE;
    }

    public void on() {
        System.out.println(" dvd on ");
    }
    public void off() {
        System.out.println(" dvd off ");
    }

    public void play() {
        System.out.println(" dvd is playing ");
    }

    //....
    public void pause() {
        System.out.println(" dvd pause ..");
    }

}
