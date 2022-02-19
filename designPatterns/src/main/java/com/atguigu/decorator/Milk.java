package com.atguigu.decorator;

/**
 *  牛奶 - 装饰者模式案例中 具体的调味品-牛奶 实体类
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDescription(" 牛奶 ");
        // 当前调味品的价格
        setPrice(3F);
    }
}
