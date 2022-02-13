package com.atguigu.singleton.type2;


/**
 * 饿汉式(静态变量)
 * 可用，可能造成内存浪费
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class SiingletonTest02 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance.equals(instance2));
        System.out.println(instance.hashCode() == instance2.hashCode());
    }
}

/** 饿汉式（静态代码块）
 * 1.构造器私有化，外部不能new
 * 2.本类内部创建对象实例
 * 3.在静态代码块中初始化对象实例
 * 3.提供一个共有的静态方法，返回实例对象
 * */
class Singleton {
    private Singleton() {
    }

    private static Singleton INSTANCE;

    static {
        INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return INSTANCE;
    }
}

