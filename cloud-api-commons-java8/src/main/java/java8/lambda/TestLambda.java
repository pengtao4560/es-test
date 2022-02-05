package java8.lambda;

import java8.stream.Employee;
import java8.stream.StreamAPITest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Lambda学习【尚硅谷java8 第二节课】
 * @author: peng tao
 * @create: 2022-01-31 21:01
 */
public class TestLambda {
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> integers = new TreeSet<>(comparator);
    }
    /**/
    @Test
    public void lambdaTest1() {
        Comparator<Integer> comparartor = (o1, o2) -> Integer.compare(o1, o2);
        Comparator<Integer> comparartor2 = Integer::compare;

    }
    List<Employee> employeeList = StreamAPITest.getEmployeeList();


    //需求：获取公司中年龄小于 35 的员工信息
    public List<Employee> filterEmployeeAge(List<Employee> emps){
        List<Employee> list = new ArrayList<>();

        for (Employee emp : emps) {
            if(emp.getAge() <= 35){
                list.add(emp);
            }
        }

        return list;
    }

    //需求：获取公司中工资大于 5000 的员工信息
    public List<Employee> filterEmployeeSalary(List<Employee> emps){
        List<Employee> list = new ArrayList<>();

        for (Employee emp : emps) {
            if(Integer.valueOf(emp.getSalary().toString()) >= 5000){
                list.add(emp);
            }
        }

        return list;
    }
    @Test
    public void test4(){
        List<Employee> list = filterEmployee(employeeList, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(employeeList, new FilterEmployeeBySalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }


    /**
     优化方式一：策略设计模式: 给到什么策略，就按什么策略过滤,参数中是策略接口
     策略模式比以上最原始的foreach判断的好处是：
     无需改动原来的类，只需要新加策略类实现 策略接口(在本例中就是
     FilterEmployeeByAge/FilterEmployeeBySalary 类实现了MyPredicate策略接口。 缺点可能是 增加类的个数
     @see FilterEmployeeByAge
     @see FilterEmployeeBySalary
     */
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();

        for (Employee employee : emps) {
            if(mp.test(employee)){
                list.add(employee);
            }
        }

        return list;
    }

    /** 优化方式二 ： 匿名内部类 */
    @Test
    public void test5() {
        List<Employee> employees = filterEmployee(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <= 24;
            }
        });
        employees.stream().forEach(System.out::println);
    }

    /** 优化方式三： Lambda表达式 */
    @Test
    public void test6 () {
        List<Employee> filterEmployee = filterEmployee(employeeList, employee -> employee.getAge() <= 23);
        filterEmployee.forEach(System.out::println);
    }

    /** 优化方式四 stream API */
    @Test
    public void test7() {
        employeeList.stream().filter(employee -> employee.getAge() >= 30)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("-------------");

        employeeList.stream().map(Employee::getUserName)
                .forEach(System.out::println);
    }

}
