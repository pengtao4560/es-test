package javase.effectivejava.c31;

import javase.effectivejava.c29.MyNewStack;
import javase.effectivejava.c30.TestGenericMethod;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;


/**
 * @description: 测试c31
 * @author: peng tao
 * @create: 2022-02-09 14:10
 */
public class TestUserStackPushAll {

    MyNewStack<Number> numberMyNewStack = new MyNewStack<>();
    Iterable<Integer> integers = new ArrayList<Integer>() {
        {
            add(11);
            add(12);
        }
    };

    /**
     * @see MyNewStack#pushAllFailed(Iterable) ;
     * */
    @Test
    public void testPushAll() {

        // 此处编译器报错
        // numberMyNewStack.pushAllFailed(integers);

        // 优化方案
        numberMyNewStack.pushAll(integers);
    }

    @Test
    public void testPopAll() {
        Collection<Object> objects = new ArrayList<Object>() {
            {
                add(11);
                add("pengtao");
            }
        };
        // 此处编译器报错
        // numberMyNewStack.popAllOld(objects);

        // 优化方案
        numberMyNewStack.popAll(objects);
    }

    @Test
    public void testUnion() {
        Set<Integer> integers = // Set.of(1, 3, 5);
                Collections.emptySet();
        Set<Double> doubles = //  Set.of(2.0, 4.0, 6.0);
        Collections.emptySet();

        Set<Number> numbers = TestGenericMethod.union(integers, doubles);
    }

    @Test
    public void testMax() {
        List<ScheduledFuture<?>> scheduledFutureList = new ArrayList<>();
        // TestGenericMethod.max(scheduledFutureList);
        // 编译报错： reason: Incompatible equality constraint: Delayed and ScheduledFuture<?>
    }

    public static <E> void swap1(List<E> list, int i, int j) {

    }
    public static <E> void swap(List<? extends E> list, int i, int j) {
        // list.set(i, list.set(j, list.get(i)));
        swapHelper(list, i, j);
    }
    // Replaces the element at the specified position in this list with the specified element (optional operation).
    // list特殊位置的元素替换为 另一个元素
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    @Test
    public void testSet() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.set(2, 233);
        System.out.println(integerList);
    }

}
