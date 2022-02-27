package com.runoob.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式-图形工场
 */
public class ShapeFactory {

    public static final Map<String, Circle> CIRCLE_MAP = new HashMap<>();

    public static Shape getCircle(String color) {

        if (!CIRCLE_MAP.containsKey(color)) {
            Circle circle = new Circle(color);
            CIRCLE_MAP.put(color, circle);
        }
        return CIRCLE_MAP.get(color);
    }


    /**
     * 获取池中 总数（池中有多少个 类型）
     * */
    public static int getCirCileColorCategoryCount() {
        return CIRCLE_MAP.size();
    }
}
