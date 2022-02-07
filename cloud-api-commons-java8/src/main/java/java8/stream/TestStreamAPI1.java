package java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStreamAPI1 {

    List<Employee> employeeList = StreamAPITest.getEmployeeList();


    //2. 中间操作
	/*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test1(){
        Stream<String> str = employeeList.stream()
                .map((e) -> e.getUserName());

        System.out.println("-------------------------------------------");

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        Stream<String> stream = strList.stream()
                .map(String::toUpperCase);

        stream.forEach(System.out::println);

        Stream<Stream<Character>> stream2 = strList.stream()
                .map(TestStreamAPI1::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");

        Stream<Character> stream3 = strList.stream()
                .flatMap(TestStreamAPI1::filterCharacter);

        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /*
        sorted()——自然排序（Comparable）
        sorted(Comparator com)——定制排序
     */

    @Test
    public void test2(){
        /* 自然排序 */
        employeeList.stream()
                .map(Employee::getUserName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");
        /* 定制排序 */
        employeeList.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getUserName().compareTo(y.getUserName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
