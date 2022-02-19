package com.atguigu.decorator;

/**
 * 装饰者模式案例实体类 - 继承了 饮品类的 咖啡类
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class Coffee extends  Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }
}
