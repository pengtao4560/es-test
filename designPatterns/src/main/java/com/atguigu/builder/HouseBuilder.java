package com.atguigu.builder;

/**
 * 抽象的建造者
 *
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
public abstract class HouseBuilder {

    protected  House house = new House();

    public abstract  void buildBasic();
    public abstract  void buildWalls();
    public abstract  void roofed();

    /**建造房子好，将产品（房子） 返回*/
    public House buildHouse() {

        return house;
    }
}
