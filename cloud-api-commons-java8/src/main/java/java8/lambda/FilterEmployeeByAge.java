package java8.lambda;

import java8.stream.Employee;

/**
 * @description: 实现断言接口，类作用为员工实体类的年龄过滤
 * @author: peng tao
 * @create: 2022-02-01 10:41
 */
public class FilterEmployeeByAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
