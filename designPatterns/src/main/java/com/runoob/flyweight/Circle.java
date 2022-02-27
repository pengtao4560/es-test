package com.runoob.flyweight;

import lombok.Data;

/**
 * 享元模式--实现了图形接口的实现类
 */
@Data
public class Circle implements Shape {

    private String color;
    private int x;
    private int y;
    /**半径 */
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
                +", x : " + x +", y :" + y +", radius :" + radius);
    }
}
