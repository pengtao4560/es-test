package javase.effectivejava.c26;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 具体说明参数化类型和原始类型的区别demo
 * @author: peng tao
 * @create: 2022-01-29 13:24
 */
public class UseaParameterizedType {
    // Fails at runtime - unsafeAdd method uses a raw type (List)!
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        // unsafeAdd(strings, Integer.valueOf(42));
        String s = strings.get(0); // Has compiler-generated cast
    }
    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }
    private static void unsafeAdd2(List<Object> list, Object o) {
        list.add(o);
    }

}
