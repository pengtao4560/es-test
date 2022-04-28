package java8.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author: peng tao
 * @create: 2021-11-03 17:14
 */
@Data
@NoArgsConstructor
@Builder
public class Employee {
    int sortNo;
    String userName;
    String gender;
    int age;
    String dept;
    BigDecimal salary;
    Status status;
    public Employee(int age) {
        this.age = age;
    }

    public Employee(String userName) {
        this.userName = userName;
    }

    public Employee(int age, String userName) {
        this.userName = userName;
        this.age = age;
    }

    public Employee(int sortNo, String userName, String gender, int age, String dept, BigDecimal salary) {
        this.sortNo = sortNo;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.dept = dept;
        this.salary = salary;
    }

    public Employee(int sortNo, String userName, String gender, int age, String dept, BigDecimal salary, Status status) {
        this.sortNo = sortNo;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.dept = dept;
        this.salary = salary;
        this.status = status;
    }
    /** 状态枚举 */
    public static enum Status {
        FREE, BUSY, VOCATION;
    }
}
