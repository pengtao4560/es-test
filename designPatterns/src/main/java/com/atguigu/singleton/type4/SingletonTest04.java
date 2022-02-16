package com.atguigu.singleton.type4;

/**
 * 懒汉式-线程安全
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */

public class SingletonTest04 {

    public static void main(String[] args) {
        System.out.println("懒汉式2 ， 线程安全~");
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }

}

// 懒汉式(线程安全，同步方法)
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    //提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
    //即懒汉式
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
