package com.atguigu.principle.liskov;

/**
 * 里氏替换原则
 *
 * @author pengtao
 * @createdate 2022/02/12 0012
 */
public class LiskovTestImporve {
    public static void main(String[] args) {
        Subtract subtract = new Subtract();
        System.out.println("11-3=" + subtract.subtract(11, 3));
        System.out.println("1-8=" + subtract.subtract(1, 8));

        System.out.println("-----------");
        SubSubtractSubClassPlus subSubtractSubClass = new SubSubtractSubClassPlus();
        // 因为 类不再继承 。因此调用者不会再认为是求减法
        System.out.println("11+3=" + subSubtractSubClass.sum(11, 3));
        System.out.println("1+8=" + subSubtractSubClass.sum(1, 8));

        System.out.println("11+3+9=" + subSubtractSubClass.subtract2(11, 3));

        // 使用组合仍然可以使用 SubtractPlus类相关方法
        System.out.println("11-3=" + subSubtractSubClass.subtract(11, 3));
    }
}

/**
 * 创建一个更加基础的基类, 把更加基础的方法和成员写到Base类中
 */
class Base {

}
class SubtractPlus extends  Base{
    int subtract(int num1, int num2) {
        return num1 - num2;
    }
}

class SubSubtractSubClassPlus extends Base {
    // 如果B需要使用A类的方法，使用组合关系
    private SubtractPlus subtractPlus = new SubtractPlus();
    /*
    * */
    int sum(int num1, int num2) {
        return num1 + num2;
    }
    /** 增加一个新功能，完成两个数相加，然后再加9*/
    int subtract2(int num1, int num2) {
        return subtract(num1, num2) + 9;
    }

    // 我们仍然想使用A的方法
    int subtract(int num1, int num2) {
        return subtractPlus.subtract(num1, num2);
    }
}
