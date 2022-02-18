package com.atguigu.bridge;

/**
 * 手机
 *
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public abstract class Phone {

    /** 组合 手机品牌 */
    private PhoneBrand phoneBrand;

    public Phone(PhoneBrand phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    protected void open() {
        this.phoneBrand.open();
    }

    protected void close() {
        this.phoneBrand.close();
    }

    protected void call() {
        this.phoneBrand.call();
    }
}
