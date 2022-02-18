package com.atguigu.adapter.objectadapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象适配器 demo 调用方
 *
 * @author pengtao
 * @createdate 2022/02/17 0017
 */
@Slf4j
public class ObjectAdapterClient {
    public static void main(String[] args) {

        log.info("===== 对象适配器模式 ====");
        VoltageObjectAdapter voltageObjectAdapter = new VoltageObjectAdapter(new Voltage220V());

        Phone phone = new Phone();
        phone.charging(voltageObjectAdapter);
    }
}

@Slf4j
class Phone {
    /** 充电方法 */
    public void charging(VoltageObjectAdapter voltageObjectAdapter) {
        if (voltageObjectAdapter.output5V() == 5) {
            log.info("电压为5V, 可以充电");
        } else if (voltageObjectAdapter.output5V() > 5) {
            log.info("电压大于5V， 不可以充电");
        } else {
            log.info("电压小于5V， 不可以充电");
        }

    }
}
