package com.atguigu.factory.factorymethod.order;

import com.atguigu.dpenum.PizzaEnum;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 订购披萨类
 *
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public abstract class OrderPizza {
    /**
     * 定义一个抽象方法 createPizza， 让各个工厂子类自己实现
     */
    abstract Pizza createPizza(PizzaEnum orderType);

    public OrderPizza() {
        Pizza pizza = null;
        // 披萨订购的类型
        PizzaEnum orderType;
        do {
            orderType = getType();
            // 抽线每个方法，由工厂子类完成
            pizza = createPizza(orderType);
            /*制作pizza的过程；*/
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    /**
     * 写一个方法，可以获取客户希望订购的披萨种类
     */
    private PizzaEnum getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请在控制台输出 input pizza 种类: 1. greek 2.cheese 3.pepper");
            String str = strin.readLine();
            return switch(Integer.parseInt(str)) {
                case 1 -> PizzaEnum.GREEK;
                case 2 -> PizzaEnum.CHEESE;
                case 3 -> PizzaEnum.PEPPER;
                default -> throw new IllegalStateException("Unexpected value: " + Integer.parseInt(str));
            };
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
