package com.atguigu.factory.factorymethod.pizza;

import com.atguigu.bean.pizza.Pizza;

/**
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class LondonPepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("伦敦胡椒披萨准备原材料");
        setName("伦敦胡椒披萨");
    }
}
