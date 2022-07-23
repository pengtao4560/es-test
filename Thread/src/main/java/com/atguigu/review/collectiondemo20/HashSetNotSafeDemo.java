package com.atguigu.review.collectiondemo20;


import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 */
public class HashSetNotSafeDemo {
    public static void main(String[] args) {
        // hashSetNotSafe();

        // fixHashSetNotSafeByDecorate();

        fixHashSetNotSafeByCopyOnWriteArraySet();

        HashSet<Object> set = new HashSet<>();//HashSet底层数据结构是 HashMap
        // Constructs a new, empty set; the backing HashMap instance has default initial capacity (16) and load factor (0.75).
        set.add("a");
        // private static final Object PRESENT = new Object();
        // value是恒定的 PRESENT 对象

    }
    /**
     * 演示 HashSet 线程不安全
     */
    @Test
    public void testHashSetNotSafe() {
        hashSetNotSafe();
    }
    /**
     *  HashSet 线程不安全解决方案：Collections.synchronizedSet
     */
    @Test
    public void testFixHashSetNotSafeByDecorate() {
        fixHashSetNotSafeByDecorate();
    }
    private static void fixHashSetNotSafeByDecorate() {
        Set<String> stringHashSet2 = Collections.synchronizedSet(new HashSet<String>());
        listForeachAdd(stringHashSet2);
    }
    /**
     *  HashSet 线程不安全解决方案：CopyOnWriteArraySet
     */
    @Test
    public void testfixHashSetNotSafeByCopyOnWriteArraySet() {
        fixHashSetNotSafeByCopyOnWriteArraySet();
    }
    private static void fixHashSetNotSafeByCopyOnWriteArraySet() {
        Set<String> stringHashSet3 = new CopyOnWriteArraySet<String>();
        listForeachAdd(stringHashSet3);
    }

    /**
     * 测试 HashSet 线程不安全
     */
    private static void hashSetNotSafe() {
        HashSet<String> stringHashSet = new HashSet<>();
        listForeachAdd(stringHashSet);
        /*
        Exception in thread "226" java.util.ConcurrentModificationException
        at java.util.HashMap$HashIterator.nextNode(HashMap.java:1429)
        at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
        at java.util.AbstractCollection.toString(AbstractCollection.java:461)
        at java.lang.String.valueOf(String.java:2982)
        at java.io.PrintStream.println(PrintStream.java:821)
        at com.atguigu.review.collectiondemo20.HashSetNotSafeDemo.lambda$listForeachAdd$0(HashSetNotSafeDemo.java:60)
        at com.atguigu.review.collectiondemo20.HashSetNotSafeDemo$$Lambda$109/897074030.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:745)
        * */
    }

    private static void listForeachAdd(Collection<String> list) {
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
