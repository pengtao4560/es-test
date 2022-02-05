package java8.lambda;

import java8.stream.Employee;
import java8.stream.StreamAPITest;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description: Lambda表达式【尚硅谷java8第四节课 练习lambda练习】
 * @author: peng tao
 * @create: 2022-02-02 09:15
 */
public class TestLambda3 {
    List<Employee> employeeList = StreamAPITest.getEmployeeList();

    @Test
    public void testLambda() {
        Collections.sort(employeeList, (e1, e2) -> {
            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if(compare == 0) {
                return e1.getUserName().compareTo(e2.getUserName());
            }
            return compare;
        });
        Collections.reverse(employeeList);
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testLambda2() {
        String testUpperCase = getUpperCase("let me get one more", (str) -> str.toUpperCase());
        System.out.println(testUpperCase);
        String testUpperCase2 = getUpperCase("let me get one more", (str) -> str.substring(11,19));
        System.out.println(testUpperCase2);
    }
    /** 对字符串进行处理 第二个参数为函数式接口*/
    public String getUpperCase(String str, MyStringUtilInterFace<String> myFun) {
        return myFun.getValue(str);
    }

    @Test
    public void testLambda3() {
        long sumLong = sumLong(1L, 2L, (x, y) -> x + y);
        System.out.println(sumLong);

    }

    /** 对两个Long 型数据进行处理 */
    public long sumLong(Long long1, Long long2, MyFunction<Long, Long> mf) {
        return mf.getValue(long1, long2);
    }

}
