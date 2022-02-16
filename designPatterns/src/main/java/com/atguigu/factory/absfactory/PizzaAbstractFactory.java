package com.atguigu.factory.absfactory;

import com.atguigu.bean.pizza.Pizza;
import com.atguigu.dpenum.PizzaTypeEnum;

/**
 * 一个抽象工厂模式的抽象层（接口）
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
public interface PizzaAbstractFactory {

    /** 创建披萨的抽象方法，让AbsFactory 的工厂子类来实现 */
    public Pizza createPizza(PizzaTypeEnum pizzaEnum);
}
