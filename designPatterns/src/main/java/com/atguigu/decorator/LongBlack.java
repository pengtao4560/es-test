package com.atguigu.decorator;

/**
 * 装饰者模式案例实体类 - 继承了 咖啡类 的 美式咖啡
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class LongBlack extends Coffee {

    public LongBlack() {
        setDescription(" 美式咖啡 ");
        setPrice(35F);
    }
}
