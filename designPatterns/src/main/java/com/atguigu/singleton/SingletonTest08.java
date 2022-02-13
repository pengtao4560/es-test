package com.atguigu.singleton;

/**
 * @author pt
 * @date 2022/02/13 0013 - 19:44
 */
public class SingletonTest08 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INISTANCE;
        Singleton instance2 = Singleton.INISTANCE;

        System.out.println(instance.equals(instance2));
        System.out.println(instance.hashCode() == instance2.hashCode());
    }
}
/** 使用枚举实现单例模式 */
enum Singleton {
    INISTANCE;

    public void sayOK() {
        System.out.println("ok");
    }
}
