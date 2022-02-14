package com.atguigu.factory.simplefactory.pizzastroe.pizza;

/**
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作胡椒披萨准备原材料");

    }
}
