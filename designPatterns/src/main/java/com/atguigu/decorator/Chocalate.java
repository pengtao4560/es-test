package com.atguigu.decorator;

/**
 * 巧克力 - 装饰者模式案例中 具体的调味品 实体类
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Chocalate extends Decorator {

    public Chocalate(Drink drink) {
        super(drink);
        setDescription(" 巧克力 ");
        // 当前调味品的价格
        setPrice(5F);
    }
}
