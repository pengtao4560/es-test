package com.atguigu.bridge;

/**
 * 折叠屏 手机 继承了 手机抽象类
 *
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public class FoldedPhone extends Phone {

    public FoldedPhone(PhoneBrand phoneBrand) {
        super(phoneBrand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("折叠样式手机开机");

    }

    @Override
    protected void close() {
        super.close();
        System.out.println("折叠样式手机关机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("折叠样式手机打电话");
    }
}
