package com.atguigu.factory.simplefactory.pizzastroe;

import com.atguigu.factory.simplefactory.pizzastroe.order.OrderPizza;

/**
 * 相当于一个客户端，发出订购任务
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class PizzaStore {
    public static void main(String[] args) {
       // OrderPizza orderPizza = new OrderPizza();

        SimpleFactory simpleFactory = new SimpleFactory();
        OrderPizza orderPizza = new OrderPizza(simpleFactory);

    }
}
