package javase.effectivejava.c30;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @description: 30 优先使用泛型方法
 * @author: peng tao
 * @create: 2022-02-08 15:11
 */
public class TestGenericMethod {

    public static void main(String[] args) {
        Set<String> guys = // Set.of("Tom", "Dick", "Harry");
                Collections.emptySet();
        Set<String> stooges = // Set.of("Larry", "Moe", "Curly");
                Collections.emptySet();
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio); // [Moe, Tom, Harry, Larry, Curly, Dick]
    }

    /*
    编译器警告(鼠标放到行上)
    Raw use of parameterized class 'Set'
    Unchecked call to 'HashSet(Collection<? extends E>)' as a member of raw type 'java.util.HashSet'
    Unchecked call to 'addAll(Collection<? extends E>)' as a member of raw type 'java.util.Set'
    Try to generify "TestGenericMethod.java" 尝试优化 本类
    *   */
    public static Set unionOld(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    /**
     * 修复及优化 第31章节优化为:
     *
     * @see TestGenericMethod#unionOld
     * 的警告
     */
    // public static <E> Set<E> union(Set<E> set1, Set<E> set2) {

    public static <E> Set<E> union(Set<? extends E> set1, Set<? extends E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    /**@see Function#identity() */
    /**
     * @see UnaryOperator#identity()
     */
    // identity 标识
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    /*编译器警告：
    Unchecked cast: 'java.util.function.UnaryOperator<java.lang.Object>' to
    'java.util.function.UnaryOperator<T>'
    可以加上抑制警告注解
    */
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    @Test
    public void test() {
        String[] strArray = {"王五", "李四", "张三"};
        UnaryOperator<String> sameString = identityFunction();

        for (String s : strArray) {
            String apply = sameString.apply(s);
            System.out.println(apply);
        }

        Number[] numberArray = {1, 2.0, 3L};

        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number number : numberArray) {
            System.out.println(sameNumber.apply(number));
        }
    }
    /**
     *  <E extends Comparable<E>> E   限定类
     *  可以理解为  任何可以与自己比较的类型E。
     * */
    // Returns max value in a collection - uses recursive type bound
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);
        }
        return result;
    }

}
