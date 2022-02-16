package com.atguigu.factory.absfactory;

import com.atguigu.factory.absfactory.order.OrderPizza;

/**
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
public class PizzaStore {
    public static void main(String[] args) {
        // 模拟客户端使用 抽象工厂
        OrderPizza beijingPizza = new OrderPizza(new BeijingPizzaAbstractFactory());
        OrderPizza londonPizza = new OrderPizza(new LondonPizzaAbstractFactory());
    }
}
