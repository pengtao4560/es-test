package javase.effectivejava;/**
 * @author pt
 * @date 2022/1/28 - 10:55
 */

import java.util.AbstractList;
import java.util.Collection;

/**
 * @description: effectiveJava 19章节
 * @author: peng tao
 * @create: 2022-01-28 10:55
 */
public interface testImpl19 {
    /**
     * @implSpec
     * 该标签 java8出现， java9大量使用
     *      * Removes a single instance of the specified element from this
     *      * collection, if it is present (optional operation).  More formally,
     *      * removes an element <tt>e</tt> such that
     *      * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     *      * this collection contains one or more such elements.  Returns
     *      * <tt>true</tt> if this collection contained the specified element (or
     *      * equivalently, if this collection changed as a result of the call).
     *
     * @see Collection#remove(java.lang.Object)
     * @see AbstractList#removeRange(int, int)
     * 从此列表中删除索引介于 fromIndex（包含）和 inclusive（不含）之间的所有元素。将任何后续元素向左
     * 移（减少索引）。这个调用通过（toIndex - fromIndex）元素来缩短列表。（如果 toIndex == fromIndex，
     * 则此操作无效。）
     * 这个方法是通过列表及其子类的 clear 操作来调用的。重写这个方法利用列表内部实现的优势，可以大大
     * 提高列表和子类的 clear 操作性能。
     * 实现要求：这个实现获取一个列表迭代器，它位于 fromIndex 之前，并重复调用 ListIterator.
     * remove 和 ListIterator.next 方法，直到整个范围被删除。注意：如果 ListIterator.remove 需要
     * 线性时间，则此实现需要平方级时间。
     * 参数：   fromIndex 要移除的第一个元素的索引   toIndex 要移除的最后一个元素之后的索引
     * */
    void remove(String str);
}
