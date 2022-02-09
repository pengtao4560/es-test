package javase.effectivejava.c30;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @description: 30 优先使用泛型方法
 * @author: peng tao
 * @create: 2022-02-08 15:11
 */
public class TestGenericMethod {

    public static void main(String[] args) {
        Set<String> guys = Set.of("Tom", "Dick", "Harry");
        Set<String> stooges = Set.of("Larry", "Moe", "Curly");
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
    /**修复
     * @see TestGenericMethod#unionOld
     * 的警告*/
    public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    /**@see Function#identity() */

}
