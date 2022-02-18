package com.atguigu.adapter.classsadpter;

import lombok.extern.slf4j.Slf4j;

/**
 * 类适配器调用方
 *
 * @author pengtao
 * @createdate 2022/02/17 0017
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        log.info("=== 类适配器模式 ===");

        VoltageAdapter adapter = new VoltageAdapter();

        Phone phone = new Phone();
        phone.charging(adapter);
    }
}

@Slf4j
class Phone {
    /** 充电方法 */
    public void charging(Voltage5V voltage5V) {
        if (voltage5V.output5V() == 5) {
            log.info("电压为5V, 可以充电");
        } else if (voltage5V.output5V() > 5) {
            log.info("电压大于5V， 不可以充电");
        } else {
            log.info("电压小于5V， 不可以充电");
        }

    }
}
