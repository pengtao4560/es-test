package javase.effectivejava;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @description: 32 合理结合泛型和可变参数
 * @author: peng tao
 * @create: 2022-02-10 17:12
 */
public class TestVarrgsJudiciously {
    @Test
    public void test() {
        List<String> list = // List.of("pt");
                Collections.emptyList();
        list.add("pt");
        dangerous(list);
        // java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
        // (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
    }

    /**
     * 此方法没有可见的强制转换，但在调用一个或多个参数时抛出类型转换异常。方法最后一行有一个编译器生成的隐形转换
     * 把类型安全性破坏了。
     */
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = // List.of(42);
                Collections.emptyList();
        intList.add(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution  堆污染
        String s = stringLists[0].get(0); //ClassCastException
    }


}
