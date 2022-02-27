package com.runoob.flyweight;

/**
 * 享元模式模拟客户端调用
 */
public class MockFlyWeightClient {
    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };

    public static void main(String[] args) {

        for(int i=0; i < 100; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
        // 不管多少次循环。圆形池 里面的颜色最多有五种类型 。
        System.out.println("count: " + ShapeFactory.getCirCileColorCategoryCount());
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}
