package javase.effectivejava;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 28 列表优于数组数组
 * @author: peng tao
 * @create: 2022-01-29 14:41
 */
public class c28ListBetterThanArray {
    public static void main(String[] args) {
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

        List<Long> ol = new ArrayList<>();
        // ol.add("I dont not fit in");


    }
}
