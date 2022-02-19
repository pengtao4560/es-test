package com.atguigu.decorator;

/**
 * 装饰者模式 - 装饰着
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {

        this.drink = drink;
    }

    @Override
    public float cost() {
        // getPrice 自己价格 + 组合的饮品的价格
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        // 这里 drink.getDescription() 输出被装饰者的信息
        return super.getDescription() + "" + super.getPrice() + " " + drink.getDescription();
    }
}
