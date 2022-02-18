package com.atguigu.bridge;

/**
 * 小米手机
 *
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public class XiaoMi implements PhoneBrand {
    @Override
    public void open() {
        System.out.println("小米手机开机");

    }

    @Override
    public void close() {
        System.out.println("小米手机关机");

    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
