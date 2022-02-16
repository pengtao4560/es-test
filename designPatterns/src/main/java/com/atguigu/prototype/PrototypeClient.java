package com.atguigu.prototype;

/**
 * 原型模式
 *
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
public class PrototypeClient {

    public static void main(String[] args) throws CloneNotSupportedException {

        Sheep tomSheep = new Sheep("tom", 1, "白色");
        // 传统方法
        Sheep sheep1 = new Sheep(tomSheep.getName(), tomSheep.getAge(), tomSheep.getColor());
        Sheep sheep2 = new Sheep(tomSheep.getName(), tomSheep.getAge(), tomSheep.getColor());

        System.out.println(sheep1);
        System.out.println(sheep2);

        System.out.println("---使用原型模式进行克隆--------");

        Sheep sheep11 = (Sheep) tomSheep.clone();
        Sheep sheep12 = (Sheep) tomSheep.clone();
        System.out.println(sheep11);
        System.out.println(sheep12);

    }
}
