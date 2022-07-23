package review.reference72;

/**
 * 强引用
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        // 这样定义的默认就是强应用
        Object obj1 = new Object();

        // 使用第二个引用，指向刚刚创建的Object对象
        Object obj2 = obj1;

        // 置空
        obj1 = null;

        // 垃圾回收
        System.gc();

        System.out.println(obj1); // o1 已经置空， null

        System.out.println(obj2);  // o2是强引用，不会被垃圾回收
    }
}
