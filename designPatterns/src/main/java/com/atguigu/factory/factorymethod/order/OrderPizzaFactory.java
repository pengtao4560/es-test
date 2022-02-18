package com.atguigu.factory.factorymethod.order;

import com.atguigu.dpenum.PizzaTypeEnum;
import com.atguigu.bean.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 订购披萨类
 *
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public abstract class OrderPizzaFactory {
    /**
     * 定义一个抽象方法 createPizza， 让各个工厂子类自己实现
     */
    abstract Pizza createPizza(PizzaTypeEnum orderType);

    public OrderPizzaFactory() {
        Pizza pizza = null;
        // 披萨订购的类型
        PizzaTypeEnum orderType;
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
     * 可以获取客户希望订购的披萨种类的方法(控制台输入指令)
     */
    public static PizzaTypeEnum getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请在控制台输出订购 pizza 种类: 1. greek 2.cheese 3.pepper");
            String str = strin.readLine();
            return getPizzaEnum(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PizzaTypeEnum getPizzaEnum(String str) {
    /*    return switch (Integer.parseInt(str)) {
            case 1 -> PizzaEnum.GREEK;
            case 2 -> PizzaEnum.CHEESE;
            case 3 -> PizzaEnum.PEPPER;
            default -> throw new IllegalStateException("Unexpected value: " + Integer.parseInt(str));
        };*/
        if ("1".equalsIgnoreCase(str)) {
            return PizzaTypeEnum.GREEK;
        } else if ("2".equalsIgnoreCase(str)) {
            return PizzaTypeEnum.CHEESE;
        } else if ("3".equalsIgnoreCase(str)) {
            return PizzaTypeEnum.PEPPER;
        } else {
            return null;
        }
    }
}
