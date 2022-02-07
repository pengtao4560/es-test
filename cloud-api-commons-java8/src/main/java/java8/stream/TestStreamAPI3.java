package java8.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 尚硅谷java8-12节
 * @author: peng tao
 * @create: 2022-02-05 09:17
 */
public class TestStreamAPI3 {

    public static final List<Employee> EMPLOYEE_LIST = StreamAPITest.getEmployeeList();

    //3. 终止操作
	/*
	    归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test3() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 以 identity 0 为x，以 集合intList的值为y进行计算结果为x 再取intList的第二个值 ···计算，得到规约reduce
        Integer sum = intList.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        Optional<Integer> sum2Optional = intList.stream().reduce((x, y) -> x + y);
        Integer sum2 = sum2Optional.get();
        System.out.println(sum2);

        System.out.println("----------------------------------------");

        Stream<BigDecimal> bigDecimalStream = EMPLOYEE_LIST.stream().map(Employee::getSalary);
        bigDecimalStream.reduce((x,y) -> x.add(y));
    }

    /** 收集： collect--将流转换为其他形式，接受一个Collector接口的实现，
     * 用于给Stream中元素做汇总的方法*/
    @Test
    public void testCollect() {

        List<String> stringList = EMPLOYEE_LIST.stream().map(Employee::getUserName)
                .collect(Collectors.toList());
        stringList.forEach(System.out::print);
        System.out.println("----------------------------------------");

        Set<String> stringSet = EMPLOYEE_LIST.stream().map(Employee::getDept)
                .collect(Collectors.toSet());
        stringSet.forEach(System.out::print);
        System.out.println("----------------------------------------");

        HashSet<Integer> collect = EMPLOYEE_LIST.stream()
                .map(Employee::getSortNo)
                .collect(Collectors.toCollection(HashSet::new));

    }

    @Test
    public  void test5() {
        // 总数
        Long count = EMPLOYEE_LIST.stream()
                .collect(Collectors.counting());

        System.out.println(count);
        long count1 = EMPLOYEE_LIST.stream().count();
        System.out.println(count1);

        // 平均值
        Double avgAge = EMPLOYEE_LIST.stream()
                .collect(Collectors.averagingDouble(Employee::getAge));

        // 总和
        Double sum = EMPLOYEE_LIST.stream()
                .collect(Collectors.summingDouble(Employee::getAge));

        // 最大值
        Optional<Employee> collect = EMPLOYEE_LIST.stream()
                .collect(Collectors.maxBy(
                        (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));
        System.out.println("年龄最大值为: " + collect.get().getAge());

        // 最小值
        Optional<BigDecimal> minSalaryOptional = EMPLOYEE_LIST.stream()
                .collect(Collectors.minBy((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()))
                ).map(Employee::getSalary);
        BigDecimal bigDecimal = minSalaryOptional.get();
        System.out.println("最小工资为:" + bigDecimal);

    }
    @Test
    public void testGroupBy() {
        Map<String, List<Employee>> collect =
                EMPLOYEE_LIST.stream().collect(Collectors.groupingBy(Employee::getDept));
        for (Map.Entry<String, List<Employee>> stringListEntry : collect.entrySet()) {
            System.out.println(stringListEntry.getKey());
            List<Employee> valueList = stringListEntry.getValue();
            valueList.forEach(System.out::println);
            System.out.println("------测试分组-------");
        }
    }

    //多级分组
    @Test
    public void test6(){
        Map<Status, Map<String, List<Employee>>> map = EMPLOYEE_LIST.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test7() {
        // Collectors.partitioningBy(Predicate<T extends Object> predicate);
        Map<Boolean, List<Employee>> map = EMPLOYEE_LIST.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() >= 30));
        List<Employee> aTrue = map.get(true);
        System.out.println(aTrue);
        System.out.println("---------");
        List<Employee> aFalse = map.get(false);
        System.out.println(aFalse);

    }

    //
    @Test
    public void test8(){
        String str = EMPLOYEE_LIST.stream()
                .map(Employee::getUserName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    @Test
    public void test9(){
        Optional<BigDecimal> sum = EMPLOYEE_LIST.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(BigDecimal::add));

        System.out.println(sum.get());
    }
}
