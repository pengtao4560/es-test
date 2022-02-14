package com.atguigu.factory.simplefactory.pizzastroe.pizza;

/**
 * @author pengtao
 * @createdate 2022/02/13 0013
 */

/**
 * 希腊披萨
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作希腊披萨准备原材料");
    }
}
