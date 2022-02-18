package com.atguigu.bridge;

/**
 * 手机品牌接口
 *
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public interface PhoneBrand {

    /** 开机 */
    void open();
    /** 关机 */
    void close();
    /** 打电话 */
    void call();
}
