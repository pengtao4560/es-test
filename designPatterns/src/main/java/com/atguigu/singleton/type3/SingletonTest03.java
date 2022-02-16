package com.atguigu.singleton.type3;

/**
 * 懒汉式-线程不安全
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */

public class SingletonTest03 {

    public static void main(String[] args) {
        System.out.println("懒汉式1 ， 线程不安全~");
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }

}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
    //即懒汉式
    public static LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
