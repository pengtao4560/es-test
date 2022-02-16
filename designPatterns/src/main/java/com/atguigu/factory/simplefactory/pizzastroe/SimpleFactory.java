package com.atguigu.factory.simplefactory.pizzastroe;

import com.atguigu.bean.pizza.CheesePizza;
import com.atguigu.bean.pizza.GreekPizza;
import com.atguigu.bean.pizza.PepperPizza;
import com.atguigu.bean.pizza.Pizza;

/**
 * 简单工厂模式-简单工厂类
 * @author pengtao
 * @createdate 2022/02/14
 */
public class SimpleFactory {

    /**简单工厂模式又叫：静态工厂模式*/
    public static Pizza createPizza2(String orderType) {

        System.out.println("使用简单工厂模式");
        Pizza pizza = null;

        if ("greek".equalsIgnoreCase(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if ("cheese".equalsIgnoreCase(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if ("peper".equalsIgnoreCase(orderType)) {
            pizza = new PepperPizza();
        }

        return pizza;
    }

    /** 根据orderType 创建Pizza对象 */
    public Pizza createPizza(String orderType) {
        System.out.println("使用简单工厂模式");
        Pizza pizza = null;

        if ("greek".equalsIgnoreCase(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if ("cheese".equalsIgnoreCase(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if ("peper".equalsIgnoreCase(orderType)) {
            pizza = new PepperPizza();
        }

        return pizza;
    }
}
