package com.atguigu.builder.old;

/**
 * 建造者模式 - 抽象房子父类
 *
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
public abstract class AbstractHouse {

    /** 打地基 */
    public abstract void buildBasic();
    /** 砌墙 */
    public abstract void buildWalls();
    /** 封顶 */
    public abstract void roofed();

    /** 建造房子整体流程 */
    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}
