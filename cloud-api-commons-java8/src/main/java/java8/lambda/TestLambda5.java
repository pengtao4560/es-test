package java8.lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *   java8及以后 内置四大核心函数式接口：
 *     消费型接口(有去无回happy消费)：Comsumer<T>
 *         void accept(T t)
 *     供给型接口(无去有回供给ji)：Supplier<T>
 *         T get();
 *     函数式接口(T参数R返回值)：Function<T, R>
 *         R apply(T t);
 *     断言型接口: Predicate<T>
 *         boolean: test(T t);
 * @description: 尚硅谷java8新特性第五节课四大内置核心函数式接口
 * @author: peng tao
 * @create: 2022-02-03 09:11
 */
public class TestLambda5 {
    /**
     * 消费型接口(有去无回happy消费)：Comsumer<T>
     */
    @Test
    public void  testConsumerFunction() {
        happyConsumer(new BigDecimal("1000"), (x) -> {
            System.out.println("买个显示器消费了" + x);
        });
    }

    public void happyConsumer(BigDecimal bigDecimal, Consumer<BigDecimal> consumer) {
        consumer.accept(bigDecimal);
    }

    /**
     * 供给型接口(无去有回供给ji)：Supplier<T>
     */
    @Test
    public void testSupplierFunction() {
        List<Integer> numberList = getNumber(3, () -> (int) (Math.random() * 1000));
        numberList.forEach(System.out::println);
    }

    /**需求：产生一些整数并返回到到集合中*/
    public List<Integer> getNumber(int count, Supplier<Integer> supplier) {
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numList.add(supplier.get());
        }
        return numList;
    }

    /**
     * 函数式接口(T参数R返回值)：Function<T, R>
     * R apply(T t);*/
    @Test
    public void test1() {
        String resultStr1 = getStrByInteger(11, (t) -> t + "人一队一起踢足球");
        String resultStr2 = getStrByInteger(5, (t) -> t + "人一队上场打篮球");
        System.out.println(resultStr1);
        System.out.println(resultStr2);
    }
    public String getStrByInteger(Integer integer, Function<Integer, String> function) {
        return function.apply(integer);
    }

    /** 断言型接口*/
    @Test
    public void testPredicateInterface() {
        boolean strIsEmpty = testStr(" ", (str) -> StringUtils.isEmpty(str));
        boolean strIsBlank = testStr(" ", (str) -> StringUtils.isBlank(str));
        System.out.println(strIsEmpty);
        System.out.println(strIsBlank);
    }

    public boolean testStr(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }

    //Predicate<T> 断言型接口：
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);

        for (String str : strList) {
            System.out.println(str);
        }
    }

    /**
     * 需求：将满足条件的字符串，放入集合中
     */
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }

        return strList;
    }
}
