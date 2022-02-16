package com.atguigu.builder;

/**
 * 建造者模式调用方
 *
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
public class Client {
    public static void main(String[] args) {
        // 盖普通的房子
        HouseBuilder commonHouseBuilder = new CommonHouseBuilder();
        // 创建房子的指挥者
        HouseDirector commonHouseDirector = new HouseDirector(commonHouseBuilder);

        // 完成盖房子， 返回产品（房子）
        House house = commonHouseDirector.build();

        // 盖高楼
        HouseBuilder highBuildingBuiler = new HighBuildingBuiler();
        // 创建房子的指挥者
        HouseDirector highBuildingDirector = new HouseDirector(commonHouseBuilder);
        // 重置建造者
        highBuildingDirector.setHouseBuilder(highBuildingBuiler);

        //完成盖房子，发挥产品（高楼)
        House build = highBuildingDirector.build();
    }
}
