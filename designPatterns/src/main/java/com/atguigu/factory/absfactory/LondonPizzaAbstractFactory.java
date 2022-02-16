package com.atguigu.factory.absfactory;

import com.atguigu.bean.pizza.Pizza;
import com.atguigu.dpenum.PizzaTypeEnum;
import com.atguigu.factory.factorymethod.pizza.LondonCheesePizza;
import com.atguigu.factory.factorymethod.pizza.LondonPepperPizza;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现 PizzaAbstractFactory 的伦敦披萨工厂子类
 *
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
@Slf4j
public class LondonPizzaAbstractFactory implements PizzaAbstractFactory {
    @Override
    public Pizza createPizza(PizzaTypeEnum pizzaEnum) {
        log.info("使用抽象工厂模式创建披萨");

        Pizza pizza = null;
        if (PizzaTypeEnum.CHEESE.equals(pizzaEnum)) {
            return new LondonCheesePizza();
        } else if(PizzaTypeEnum.PEPPER.equals(pizzaEnum)) {
            return new LondonPepperPizza();
        }
        return pizza;
    }
}
