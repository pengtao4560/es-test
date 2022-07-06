package jvm;

/**
 * 打印类加载器
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        ClassLoader classLoader = obj.getClass().getClassLoader();
        System.out.println(classLoader); //结果为： null 。
        // 因为 BootStrapClassLoader是 C++语言写的，所以打印的是null

        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        ClassLoader myClassLoader = classLoaderDemo.getClass().getClassLoader();

        System.out.println(myClassLoader);// sun.misc.Launcher$AppClassLoader@14dad5dc

        // G:\jdk1.8.0_45\jre\lib\rt.jar\java\lang\
        // 为什么Object，String ArrayList等可以直接使用： 因为 rt.jar被 bootstrap 类加载器在 启动的时候就 加载进了 JVM里面

        System.out.println("----------------");
        System.out.println(myClassLoader); // sun.misc.Launcher$AppClassLoader@14dad5dc
        System.out.println(myClassLoader.getParent()); //sun.misc.Launcher$ExtClassLoader@4517d9a3
        System.out.println(myClassLoader.getParent().getParent()); // null  因为 BootStrapClassLoader是 C++语言写的，所以打印的是null

        System.out.println("============");
        System.out.println(classLoader);
        // System.out.println(classLoader.getParent()); //盘古开天辟地。 Bootstrap就是盘古，盘古就是祖先，不存在其父辈
        // System.out.println(classLoader.getParent().getParent()); // //盘古开天辟地。 Bootstrap就是盘古，盘古就是祖先，不存在其父辈，更不存在父辈的父辈
    }
}
