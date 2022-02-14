package com.atguigu.principle.liskov;

/**
 * 里氏替换原则
 *
 * @author pengtao
 * @createdate 2022/02/12 0012
 */
public class LiskovTest {
    public static void main(String[] args) {
        Subtract subtract = new Subtract();
        System.out.println("11-3=" + subtract.subtract(11, 3));
        System.out.println("1-8=" + subtract.subtract(1, 8));

        System.out.println("-----------");
        SubSubtractSubClass subSubtractSubClass = new SubSubtractSubClass();
        System.out.println("11-3=" + subSubtractSubClass.subtract(11, 3)); // 这里 本意是求出 11-3
        System.out.println("1-8=" + subSubtractSubClass.subtract(1, 8)); // 这里 本意是求出 1-8

        System.out.println("11+3+9=" + subSubtractSubClass.subtract2(11, 3));
    }
}

class Subtract {
    int subtract(int num1, int num2) {
        return num1 - num2;
    }
}

class SubSubtractSubClass extends Subtract {
    /**假定 SubSubtractSubClass的作者无意间继承Subtract并重写Subtract#substract方法，但不认识substract 是 相减
    * 违反了里氏替换原则. 改进方法：
    * @see LiskovTestImporve
    * */
    int subtract(int num1, int num2) {
        return num1 + num2;
    }
    /** 增加一个新功能，完成两个数相加，然后再加9*/
    int subtract2(int num1, int num2) {
        return subtract(num1, num2) + 9;
    }
}
