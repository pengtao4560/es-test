package com.atguigu.singleton.type7;

/**
 * 静态内部类实现单例设计模式
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
public class SingletonTest07 {

    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

    }
}
/** 静态内部类完成*/
class Singleton {

    private Singleton() {
    }
    /** 静态内部类，提供一个 Singleton 类型的静态属性 */
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    /** 提供一个静态的公有方法，直接返回 Singleton.SingletonInstance */
    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
