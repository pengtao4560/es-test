package com.atguigu.principle.singleresponsibility;

/**
 * 设计模式原则：单一职责原则
 *
 * @author pt
 * @createdate 2022/02/12 0012
 * @desc 单一职责学习demo 1
 */
public class SingleRsponsibility1 {
    public static void main(String[] args) {
        AirVehicle vehicle = new AirVehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        // 此处违反了单一职责原则
        vehicle.run("飞机");
    }
}

/**
 * ’交通工具类
 *  方式1
 * 1。在方式1的run方法中,违反了单一职责原则
 * 2. 解决的方式非常简单，根据交通工具运行方式不通，分解成不同的类即可
 * @see SingleRsponsibility2
 * */
class Vehicle {
    void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行····");
    }
}
