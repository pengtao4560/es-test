package javase.collection;

import java8.stream.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 集合测试
 */
public class CollectionTest {

    /**
     * 获取用户列表
     */
    public static List<Employee> getEmployeeList() {
        List<Employee> userList = new ArrayList<Employee>();
        userList.add(new Employee(1, "小王", "男", 32, "研发部", BigDecimal.valueOf(11600), Employee.Status.BUSY));
        userList.add(new Employee(2, "小王", "男", 33, "财务部", BigDecimal.valueOf(11600), Employee.Status.FREE));
        userList.add(new Employee(3, "小张", "男", 30, "财务部", BigDecimal.valueOf(11800), Employee.Status.BUSY));
        userList.add(new Employee(4, "小李", "女", 20, "人事部", BigDecimal.valueOf(11700), Employee.Status.VOCATION));
        userList.add(new Employee(5, "小吴", "男", 38, "研发部", BigDecimal.valueOf(11500), Employee.Status.BUSY));
        userList.add(new Employee(6, "小崔", "女", 25, "财务部", BigDecimal.valueOf(11200), Employee.Status.VOCATION));
        userList.add(new Employee(7, "小崔", "女", 25, "财务部", BigDecimal.valueOf(11200), Employee.Status.FREE));
        return userList;
    }

    /**
     * Java8 开始，可以使用 Collection#removeIf()方法删除满足特定条件的元素, 如
     * */
    @Test
    public void testRemoveIf() {
        List<Employee> employeeList = getEmployeeList();
        System.out.println(employeeList.size());
        employeeList.removeIf(employee -> employee.getAge() > 25);
        System.out.println(employeeList.size());
    }
}


