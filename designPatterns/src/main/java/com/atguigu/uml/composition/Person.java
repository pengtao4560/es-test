package com.atguigu.uml.composition;

public class Person {
    private IDCard card;
    // 人和人的头不可以分开，所以是组合关系
    private Head head = new Head();

}

class Head {
}

class IDCard {
}
