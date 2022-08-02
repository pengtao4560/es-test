package 面试基础题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ArrayToArrayList {
    public static String[] strArrar = {"a", "b", "c"};

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(strArrar);
        boolean add = strings.add("3");
        System.out.println(strings);

        List<String> listStr = new ArrayList<>();
        listStr.addAll(strings);
        boolean add1 = listStr.add("3");
        System.out.println(listStr);
    }
}
