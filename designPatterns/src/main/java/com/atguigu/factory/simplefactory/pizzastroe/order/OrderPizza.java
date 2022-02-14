package com.atguigu.factory.simplefactory.pizzastroe.order;

import com.atguigu.factory.simplefactory.pizzastroe.SimpleFactory;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.CheesePizza;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.GreekPizza;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 订购披萨类
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class OrderPizza {

/*    public OrderPizza() {
        Pizza pizza = null;
        */
    /**
     * 订购披萨的类型
     *//*
        String orderType;
        do {
            orderType = getType();
            if ("greek".equalsIgnoreCase(orderType)) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if ("cheese".equalsIgnoreCase(orderType)) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            } else {
                System.out.println("暂不支持制作其他Pizza");
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }*/
    // 定义一个简单工厂对象
    private SimpleFactory simpleFactory;
    Pizza pizza = null;

    public OrderPizza(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }

    public void setFactory(SimpleFactory simpleFactory) {
        // 订单需要用户输入
        String orderType = "";
        // 设置简单工厂对象
        this.simpleFactory = simpleFactory;
        do {
            orderType = getType();
            pizza = this.simpleFactory.createPizza(orderType);

            //
            if (!Objects.isNull(pizza)) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购Pizza失败");
                break;
            }

        } while (true);
    }

    /**
     * 写一个方法，可以获取客户希望订购的披萨种类
     */
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请在控制台输出 input pizza 种类: greek/cheese");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
