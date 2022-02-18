package com.atguigu.builder;

/**
 * @author pengtao
 * @createdate 2022/02/16 0016
 */

public class CommonHouseBuilder extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基5米 ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子砌墙10cm ");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子屋顶 ");
    }

}
