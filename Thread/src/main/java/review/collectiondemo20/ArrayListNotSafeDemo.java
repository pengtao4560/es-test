package review.collectiondemo20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Arraylist线程不安全问题及解决方案
 */
public class ArrayListNotSafeDemo {
    public static void main(String[] args) {
        fixArrayListNotSafeByCopyOnWriteArrayList();
    }
    /**
     * ArrayList线程不安全demo
     */
    @Test
    public void testArrayListNotSafe() {
        arrayListNotSafeDemo();
    }

    /**
     * ArrayList线程不安全解决方案1: Vector
     */
    @Test
    public void testFixArrayListNotSafeByVector() {
        fixArrayListNotSafeByVector();
    }

    /**
     * ArrayList线程不安全解决方案2: Collections.synchronizedCollection
     */
    @Test
    public void testFixArrayListNotSafeBysynchronizedCollection() {
        fixArrayListNotSafeBysynchronizedCollection();
    }

    /**
     * ArrayList线程不安全解决方案3： CopyOnWriteArrayList
     */
    @Test
    public void testFixArrayListNotSafeByCopyOnWriteArrayList() {
        fixArrayListNotSafeByCopyOnWriteArrayList();
    }

    /**
     * Copyonwrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器object【】添M,而是先将当前容object【】，进行Copy,
     * 夏制出一个新的容器object【】newELements,然后新的容object【】newELements里添加元素，添加完元素之后，
     * 再将原容器的引用指向新的容器setArray(newELements)j。这样微的好处是可以Copyonirite容器进行并发的读，
     * 而不需要领，因为当前容器不会添加任何元素。所以Copyonwr1te容器也是一种读写分离的想想，读和写不问同的容器
     */

    /**
     * ArrayList线程不安全演示
     */
    private static void arrayListNotSafeDemo() {
        List<String> list = new ArrayList<>();

        listForeachAdd(list);
    }

    private static void listForeachAdd(Collection<String> list) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 写时复制CopyOnWrite 在写的时候复制 list
     *
     * @see CopyOnWriteArrayList
     * @see CopyOnWriteArrayList#add(Object)
     * Appends the specified element to the end of this list. 追加一个特殊的元素到list的尾部
     *
     * 不要只是会用，会用只不过是一个API调用工程师
        底层原理？？

     * 1.故障现象
     *      java.util.ConcurrentModificationException
     * 2.导致原因
     * 并发争抢修改导致，参考我们的花名册签名情况。
     * 1个人正在写入，另外一个同学过来抢夺，导致数据不一致异常。并发修改异常。
     * 3解决方案
     * 3.1 new Vector<>();
     * 3.2 Collections.synchronizedlist(new ArrayList<>())j
     * 3.3 new CopyonwriteArrayList();
     *
     * @see Vector
     * @see Collections#synchronizedList(List)
     * @see CopyOnWriteArrayList
     */
    private static void fixArrayListNotSafeByCopyOnWriteArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();

        listForeachAdd(list);
    }

    /**
     * Collections.synchronizedCollection
     */
    private static void fixArrayListNotSafeBysynchronizedCollection() {
        List<String> list = new ArrayList<>();

        Collection<String> decoratorList = Collections.synchronizedCollection(list);
        listForeachAdd(decoratorList);
    }

    /**
     * 使用Vector 代替 ArrayList 提高安全性，并发性急剧下降。因为
     *
     * @see Vector#add(Object) 加锁synchronized
     */
    private static void fixArrayListNotSafeByVector() {
        List<String> list = new Vector<>();

        listForeachAdd(list);
    }

}
