package com.atguigu.bridge;

/**
 * Vivo手机
 *
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public class Vivo implements PhoneBrand {
    @Override
    public void open() {
        System.out.println("Vivo手机开机");

    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");

    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
