package javase.effectivejava.c29;

import java.util.Arrays;
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

}
