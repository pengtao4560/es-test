package com.atguigu.adapter.objectadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 被适配的类（对客户端使用方来说是不可见的）---220V电压类
 *  * @author pengtao
 *  * @createdate 2022/02/17 0017
 */
@Slf4j
public class Voltage220V {
    /** 输出220V 电压 */
    public int output220V() {
        int src = 220;
       log.info("电压 {} 伏", src);
       return src;
    }
}
