package com.atguigu.singleton.type6;

/**
 * @author pengtao
 * @createdate  2022/02/13 0013
 */
public class SingletonTest06 {

    public static void main(String[] args) {
        System.out.println("双重检查");
        DoubleCheckLazySingleton instance = DoubleCheckLazySingleton.getInstance();
        DoubleCheckLazySingleton instance2 = DoubleCheckLazySingleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

    }

}

// 懒汉式(线程安全，同步方法)
class DoubleCheckLazySingleton {
    private static volatile DoubleCheckLazySingleton INSTANCE;

    private DoubleCheckLazySingleton() {
    }

    /**双重检查-提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     * 同时保证了效率，推荐使用
     *
     * */
    public static synchronized DoubleCheckLazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckLazySingleton.class) {
                INSTANCE = new DoubleCheckLazySingleton();
            }
        }
        return INSTANCE;
    }

}
