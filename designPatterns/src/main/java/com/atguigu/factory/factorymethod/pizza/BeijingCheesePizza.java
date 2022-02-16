package com.atguigu.factory.factorymethod.pizza;

import com.atguigu.bean.pizza.Pizza;

/**
 * 北京奶酪披萨
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class BeijingCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("北京奶酪披萨准备原材料");
        setName("北京奶酪披萨");
    }
}
