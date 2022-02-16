package com.atguigu.bean.pizza;

/**
 * 奶酪披萨
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作奶酪披萨准备原材料");
    }
}


