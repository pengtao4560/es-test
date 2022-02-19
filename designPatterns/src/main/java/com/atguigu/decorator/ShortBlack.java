package com.atguigu.decorator;

/**
 * 装饰者模式案例实体类 - 继承了 咖啡类 的 澳式咖啡
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class ShortBlack extends Coffee {

    public ShortBlack() {
        setDescription(" 澳式咖啡 ");
        setPrice(28F);
    }
}
