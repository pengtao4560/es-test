package com.atguigu.adapter.classsadpter;

import com.atguigu.adapter.objectadapter.Voltage220V;
import lombok.extern.slf4j.Slf4j;

/**
 * 适配220V电压和5V电压的适配器类。
 *
 * @author pengtao
 * @createdate 2022/02/17 0017
 */
@Slf4j
public class VoltageAdapter extends Voltage220V implements Voltage5V {


    @Override
    public int output5V() {
        int srcV = output220V();
        int dstV = srcV / 44; //转成5V
        return dstV;
    }
}
