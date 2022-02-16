package com.atguigu.principle.singleresponsibility;

/**
 * 设计模式原则：单一职责原则
 *
 * @author pt
 * @createdate 2022/02/12 0012
 * @desc
 */
public class SingleRsponsibility2 {
    public static void main(String[] args) {
        RoadVehicle vehicle = new RoadVehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}

/**
 *  公路交通工具类 遵循单一职责原则
 *  分析：但是这样做的改动很大，即要将类分解，同时修改客户端
 *  改进：直接修改Vehicle类，改动的代码会比较少 ===》方案三：
 * @see SingleRsponsibility3
 * */
class RoadVehicle {
    void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行····");
    }
}

class AirVehicle {
    void run(String vehicle) {
        System.out.println(vehicle + "在空中上运行····");
    }
}

class WaterVehicle {
    void run(String vehicle) {
        System.out.println(vehicle + "在水上或水中运行····");
    }
}
