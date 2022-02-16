package com.atguigu.factory.absfactory;

import com.atguigu.bean.pizza.Pizza;
import com.atguigu.dpenum.PizzaTypeEnum;
import com.atguigu.factory.factorymethod.pizza.BeijingCheesePizza;
import com.atguigu.factory.factorymethod.pizza.BeijingPepperPizza;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现PizzaAbstractFactory的北京披萨工厂子类
 *
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
@Slf4j
public class BeijingPizzaAbstractFactory implements PizzaAbstractFactory {
    @Override
    public Pizza createPizza(PizzaTypeEnum pizzaEnum) {
        Pizza pizza = null;
        log.info("使用抽象工厂模式创建披萨");
        if (PizzaTypeEnum.PEPPER.equals(pizzaEnum)) {
            pizza = new BeijingPepperPizza();
        } else if (PizzaTypeEnum.CHEESE.equals(pizzaEnum)) {
            pizza = new BeijingCheesePizza();
        } else if (PizzaTypeEnum.GREEK.equals(pizzaEnum)) {
            // TODO
            pizza = null;
        }
        return pizza;
    }
}
