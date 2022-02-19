package com.atguigu.decorator;

/**
 *  豆浆 - 装饰者模式案例中 具体的调味品-豆浆 实体类
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Soy extends Decorator {

    public Soy(Drink drink) {
        super(drink);
        setDescription(" 豆浆 ");
        // 当前调味品的价格
        setPrice(2F);
    }

}
