package com.atguigu.factory.factorymethod.pizza;

import com.atguigu.factory.simplefactory.pizzastroe.pizza.Pizza;

/**
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class BeijingPepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("北京胡椒披萨准备原材料");
        setName("北京胡椒披萨");
    }
}
