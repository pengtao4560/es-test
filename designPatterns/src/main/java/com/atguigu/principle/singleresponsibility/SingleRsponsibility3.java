package com.atguigu.principle.singleresponsibility;

/**
 * @author pt
 * @createdate 2022/02/12 0012
 * @desc 单一职责原则改进方案3
 */
public class SingleRsponsibility3 {
    public static void main(String[] args) {
        VehiclePlus vehiclePlus = new VehiclePlus();
        vehiclePlus.run("汽车");
        vehiclePlus.runAir("飞机");
        vehiclePlus.runWater("轮船");
    }
}

/** 交通工具类
 * 分析：1.这种修改方法没有对原来的类做打的修改，只是增加方法
 *      2.这里虽然没有在类这个级别上遵守单一职责原则，但是在方法级别上，仍然是遵守单一职责原则
 * */
class VehiclePlus {
    void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行····");
    }
    void runAir(String vehicle) {
        System.out.println(vehicle + "在空中上运行····");
    }

    void runWater(String vehicle) {
        System.out.println(vehicle + "在水上或水中运行····");
    }

}
