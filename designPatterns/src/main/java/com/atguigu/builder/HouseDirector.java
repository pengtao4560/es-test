package com.atguigu.builder;

/**
 * 房子指挥者 HouseDirector 去指定建造房子的流程
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
public class HouseDirector {
    HouseBuilder houseBuilder = null;
    /* 构造器传入 houseBuilder */

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    /* 通过setter传入 houseBuilder */
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    /** 如何处理建造房子的流程，交给指挥者 HouseDirector */
    public House build() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
