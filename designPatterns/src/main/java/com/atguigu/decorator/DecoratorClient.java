package com.atguigu.decorator;

/**
 * 装饰者模式客户端-调用
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class DecoratorClient {

    public static void main(String[] args) {

        // 装饰者模式下的订单: 2份巧克力 + 一份牛奶的LongB1ack
        // 1. 点一份LongBlack
        Drink order = new LongBlack();
        System.out.println("费用1 = " + order.getPrice());
        System.out.println("描述1 = " + order.getDescription());

        // 2. order加入一份牛奶
        Milk milk = new Milk(order);
        System.out.println("order 加入一份牛奶 费用 = " + milk.cost());
        System.out.println("order 加入一份牛奶 描述 = " + milk.getDescription());
        System.out.println(milk.cost());

        // 3. order加入一份巧克力
        Chocalate chocalate = new Chocalate(milk);
        Drink orderCompose = new Chocalate(chocalate);

        System.out.println("order 加入一份牛奶以及2份巧克力 费用 = " + orderCompose.cost());
        System.out.println("order 加入一份牛奶以及2份巧克力 描述 = " + orderCompose.getDescription());
    }
}
