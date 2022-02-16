package com.atguigu.factory.factorymethod;

import com.atguigu.factory.factorymethod.order.BeiJingOrderPizza;

/**
 * 工厂方法模式
 *
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class pizzastore {

    public static void main(String[] args) {

        // 创建北京口味的各种Pizza
        new BeiJingOrderPizza();
    }
}
