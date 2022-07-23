package review.collectiondemo20;


import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class HashMapNotSafeDemo {

    public static void main(String[] args) {
        // hashMapNotSafe();
        fixHashMapNotSafeByConcurrentHashMap();

        fixHashMapNotSafeByCollectionsSynchronizedMap();
    }

    /**
     * 演示 HashMap 线程不安全 demo
     */
    @Test
    public void testHashMapNotSafe() {
        hashMapNotSafe();
    }

    /**
     * HashMap 线程不安全 解决方案：Collections.synchronizedMap
     */
    @Test
    public void testFixHashMapNotSafeByCollectionsSynchronizedMap() {
        fixHashMapNotSafeByCollectionsSynchronizedMap();
    }
    /**
     * 解决 HashMap 线程不安全：使用 Collections.synchronizedMap()
     *  @see Collections#synchronizedMap(Map)
     */
    private static void fixHashMapNotSafeByCollectionsSynchronizedMap() {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        addToMap(map);
    }

    /**
     * HashMap 线程不安全 解决方案：JUC的 ConcurrentHashMap
     */
    @Test
    public void testFixHashMapNotSafeByConcurrentHashMap() {
        fixHashMapNotSafeByConcurrentHashMap();
    }
    /**
     * 解决 HashMap 线程不安全：使用 java.util.concurrent.ConcurrentHashMap
     * @see ConcurrentHashMap
     */
    private static void fixHashMapNotSafeByConcurrentHashMap() {
        Map<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<String, String>();
        addToMap(stringStringConcurrentHashMap);
    }

    /**
     * 演示 HashMap 线程不安全
     */
    private static void hashMapNotSafe() {
        HashMap<String, String> map = new HashMap<>();

        addToMap(map);
    }

    private static void addToMap(Map<String, String> map) {
        for (int i = 0; i <= 300; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
