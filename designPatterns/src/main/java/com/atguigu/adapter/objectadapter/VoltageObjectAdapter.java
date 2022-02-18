package com.atguigu.adapter.objectadapter;

import com.atguigu.adapter.classsadpter.Voltage5V;

/**
 * 对象适配器
 *
 * @author pengtao
 * @createdate 2022/02/17 0017
 */
public class VoltageObjectAdapter implements Voltage5V {

    //聚合关系
    private Voltage220V voltage220V;

    @Override
    public int output5V() {
        int srcV = voltage220V.output220V();
        int dstV = srcV / 44; //适用对象适配器进行适配 转成5V
        return dstV;
    }

    public VoltageObjectAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }
}
