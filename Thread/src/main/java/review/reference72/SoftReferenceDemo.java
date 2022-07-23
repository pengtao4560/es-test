package review.reference72;

import java.lang.ref.SoftReference;

/**
 * 软引用
 */
public class SoftReferenceDemo {

    public static void softRef_Memory_Enough() {

        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);

        Object o2 = o1;

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(objectSoftReference.get());
        System.out.println("内存足够时软引用不被回收");
    }

    /**
     *      * JVM配置，故意产生大对象并配置小的内存，让它的内存不够用了导致OOM，看软引用的回收情况
     *      * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {

        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        o1 = null;
        // 模拟OOM自动GC
        try {
            // 创建30M的大对象
            byte[] bytes = new byte[50 * 1024 * 1024];
            // 增加 vm 参数启动 -Xms=10m -Xmm=10m
        } catch (Exception e) {

        } finally {
            System.out.println(o1);
            System.out.println(objectSoftReference.get());
            System.out.println("内存不够时软引用被回收");
        }
    }

    public static void main(String[] args) {
        System.out.println("------");
        softRef_Memory_Enough();
        System.out.println("------");
        System.out.println();
        softRef_Memory_NotEnough();
        System.out.println("------");

    }
}
