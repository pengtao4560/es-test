package com.atguigu.decorator;

/**
 * 装饰者模式案例实体类 - 继承了 咖啡类 的意大利咖啡
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Espresso extends Coffee {

    public Espresso() {
        setDescription(" 意大利咖啡 ");
        setPrice(46F);
    }
}
