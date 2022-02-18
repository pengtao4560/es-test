package com.atguigu.bridge;

/**
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public class UpRightPhone extends Phone {

    public UpRightPhone(PhoneBrand phoneBrand) {
        super(phoneBrand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("直立样式手机开机");

    }

    @Override
    protected void close() {
        super.close();
        System.out.println("直立样式手机关机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("直立样式手机打电话");
    }
}
