package com.atguigu.factory.factorymethod.order;

import com.atguigu.dpenum.PizzaEnum;
import com.atguigu.factory.factorymethod.pizza.LondonCheesePizza;
import com.atguigu.factory.factorymethod.pizza.LondonPepperPizza;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.Pizza;

/**
 * 北京订购披萨类
 *
 * @author pengtao
 * @createdate 2022/02/14 0014
 */
public class LondonOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(PizzaEnum pizzaEnum) {
        Pizza pizza = null;
        if (PizzaEnum.PEPPER.equals(pizzaEnum)) {
            pizza = new LondonPepperPizza();
        } else if (PizzaEnum.CHEESE.equals(pizzaEnum)) {
            pizza = new LondonCheesePizza();
        }
        return pizza;
    }
}
