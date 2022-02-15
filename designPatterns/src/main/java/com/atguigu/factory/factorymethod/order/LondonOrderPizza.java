package com.atguigu.factory.factorymethod.order;

import com.atguigu.dpenum.PizzaTypeEnum;
import com.atguigu.factory.factorymethod.pizza.LondonCheesePizza;
import com.atguigu.factory.factorymethod.pizza.LondonPepperPizza;
import com.atguigu.bean.pizza.Pizza;

/**
 * 北京订购披萨类
 *
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class LondonOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(PizzaTypeEnum pizzaEnum) {
        Pizza pizza = null;
        if (PizzaTypeEnum.PEPPER.equals(pizzaEnum)) {
            pizza = new LondonPepperPizza();
        } else if (PizzaTypeEnum.CHEESE.equals(pizzaEnum)) {
            pizza = new LondonCheesePizza();
        }
        return pizza;
    }
}
