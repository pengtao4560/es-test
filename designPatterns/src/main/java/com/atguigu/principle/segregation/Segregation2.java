package com.atguigu.principle.segregation;

/**
 * @author pt
 * @createdate 2022/02/12 0012
 * @desc
 */
public class Segregation2 {
    public static void main(String[] args) {
        APlus aPlus = new APlus();

        BPlus bPlus = new BPlus();
        aPlus.depend1(bPlus);
        aPlus.depend2(bPlus);
        aPlus.depend3(bPlus);

        CPlus cPlus = new CPlus();
        DPlus dPlus = new DPlus();
        cPlus.depend1(dPlus);
        cPlus.depend1(dPlus);
        cPlus.depend1(dPlus);
    }
}
interface  Interface1Plus {
    void operation1();
}

interface Interface2 {
    void operation2();
    void operation3();
}

interface Interface3 {
    void operation4();
    void operation5();
}
class BPlus implements Interface1Plus, Interface2 {
    public void operation1() {
        System.out.println("B 实现了 operation1");
    }

    public void operation2() {
        System.out.println("B 实现了 operation2");
    }
    public void operation3() {
        System.out.println("B 实现了 operation3");
    }
}

class DPlus implements Interface1Plus, Interface3 {
    public void operation1() {
        System.out.println("D 实现了 operation1");
    }

    public void operation4() {
        System.out.println("D 实现了 operation4");
    }
    public void operation5() {
        System.out.println("D 实现了 operation5");
    }
}

class APlus { //A 类通过接口Interface1 依赖(使用) B类，但是只会用到1,2,3方法
    public void depend1(Interface1Plus i) {
        i.operation1();
    }
    public void depend2(Interface2 i) {
        i.operation2();
    }
    public void depend3(Interface2 i) {
        i.operation3();
    }
}

class CPlus { //C 类通过接口Interface1 依赖(使用) D类，但是只会用到1,4,5方法
    public void depend1(Interface1Plus i) {
        i.operation1();
    }
    public void depend4(Interface3 i) {
        i.operation4();
    }
    public void depend5(Interface3 i) {
        i.operation5();
    }
}
