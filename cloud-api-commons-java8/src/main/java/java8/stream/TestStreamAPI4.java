package java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 尚硅谷java8 第13 streamApi练习
 * @author: peng tao
 * @create: 2022-02-05 14:26
 */
public class TestStreamAPI4 {
    /** 给定一个数字列表，返回 平方列表*/

    @Test
    public void testStream() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integerList.stream().map((x) -> x*x).collect(Collectors.toList());
        System.out.println(collect);

        Integer[] intArr = {1, 2, 3, 4, 5};
        List<Integer> intList = Arrays.stream(intArr)
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println(intList);
    }

    @Test
    public void test2() {

    }
}
