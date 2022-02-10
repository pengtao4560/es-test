package javase.effectivejava.c29;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * @description: 简单栈优化后
 * @author: peng tao
 * @create: 2022-02-08 14:47
 */
@SuppressWarnings("unchecked")
public class MyNewStack<E> {
    // Initial attempt to generify Stack - won't compile!
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public MyNewStack() {
        // 编译报错 Type parameter 'E' cannot be instantiated directly
        // elements = new E[DEFAULT_INITIAL_CAPACITY];
        /* 以上两行修改后鼠标移动到22行
        编译器回发出警告：Unchecked cast: 'java.lang.Object[]' to 'E[]'
        加上注解 @SuppressWarnings("unchecked") 后则不会警告
        */
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
    /** 需求： 想要添加一个方法来获取一系列元素并将它们全部推送到栈上。 第一种尝试*/
    public void pushAllFailed(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    /**
     * 需求：现在假设你想写一个 popAll 方法，与 pushAll 方法相对应。
     * popAll 方法从栈中弹出每个元素并将元素添加到给定的集合中。
     * 以下是第一次尝试编写 popAll 方法的过程：
     */
    public void popAllOld(Collection<E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
