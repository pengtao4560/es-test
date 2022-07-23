package review.reference72;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(objectWeakReference.get());

        System.out.println("---gc回收前后分割线----");
        System.out.println();

        o1 = null;
        System.gc(); // 未配vm参数，内存充足时
        System.out.println(o1);
        System.out.println(objectWeakReference.get());
    }
}
