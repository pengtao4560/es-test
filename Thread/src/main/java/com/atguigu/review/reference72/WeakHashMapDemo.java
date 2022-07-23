package com.atguigu.review.reference72;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * com.atguigu.review.reference72.WeakHashMapDemo
 * @see WeakHashMap
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("-------");
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer integer = new Integer(1);

        hashMap.put(integer, "HashMap");
        System.out.println(hashMap);

        integer = null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap + " size: " + hashMap.size());

    }

    /**
     * An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use.
     * WeakHashMap中的 entry（键值对）将在其键不再正常使用时自动删除。
     */
    private static void myWeakHashMap() {

        WeakHashMap<Integer, String> hashMap = new WeakHashMap<>();
        Integer integer = new Integer(2);

        hashMap.put(integer, "WeakHashMap");
        System.out.println(hashMap);

        integer = null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap + " size: " + hashMap.size());
    }
}
