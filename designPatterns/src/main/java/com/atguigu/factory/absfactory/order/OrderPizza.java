package com.atguigu.factory.absfactory.order;

import com.atguigu.bean.pizza.Pizza;
import com.atguigu.dpenum.PizzaTypeEnum;
import com.atguigu.factory.absfactory.PizzaAbstractFactory;
import com.atguigu.factory.factorymethod.order.OrderPizzaFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 订购披萨客户端类
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
@Slf4j
public class OrderPizza {
    PizzaAbstractFactory pizzaAbstractFactory;

    public OrderPizza(PizzaAbstractFactory pizzaAbstractFactory) {
        setPizzaAbstractFactory(pizzaAbstractFactory);
    }

    public PizzaAbstractFactory getPizzaAbstractFactory() {
        return pizzaAbstractFactory;
    }

    public void setPizzaAbstractFactory(PizzaAbstractFactory pizzaAbstractFactory) {

        Pizza pizza = null;
        this.pizzaAbstractFactory = pizzaAbstractFactory;

        do {
            PizzaTypeEnum pizzaEnum = getPizzaType(); // 用户输入
            pizza = pizzaAbstractFactory.createPizza(pizzaEnum);
            if (!Objects.isNull(pizza)) { // 订购成功过
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                log.info("抽象工厂方法订购披萨失败,暂不支持 {} pizza ", pizzaEnum);
                break;
            }

        } while (true);

    }

    /**
     * 写一个方法，可以获取客户希望订购的披萨种类
     */
    private PizzaTypeEnum getPizzaType() {
        return OrderPizzaFactory.getType();
    }
}
