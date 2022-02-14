package com.atguigu.principle.demeter;

/**
 * 迪米特法则改进后
 *
 * @author pengtao
 * @createdate 2022/02/12 0012
 */

import java.util.ArrayList;
import java.util.List;

//客户端
public class DemeterImporve {

    public static void main(String[] args) {
        System.out.println("~~~使用迪米特法则的改进~~~");
        //创建了一个 SchoolManager 对象
        SchoolManagerPlus schoolManager = new SchoolManagerPlus();
        //输出学院的员工id 和  学校总部的员工信息
        schoolManager.printAllEmployee(new CollegeManagerPlus());

    }

}

/** 管理学院员工的管理类 */
class CollegeManagerPlus {
    /**  返回学院的所有员工 */
    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<CollegeEmployee>();
        for (int i = 0; i < 10; i++) { //这里我们增加了10个员工到 list
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工id= " + i);
            list.add(emp);
        }
        return list;
    }

    /** 输出学院员工的信息 */
    public void printEmployee() {
        //获取到学院员工
        List<CollegeEmployee> list1 = getAllEmployee();
        System.out.println("------------学院员工------------");
        for (CollegeEmployee e : list1) {
            System.out.println(e.getId());
        }
    }
}

/** 学校管理类 */

//分析 SchoolManager 类的直接朋友类有哪些 Employee、CollegeManager
//CollegeEmployee 不是 直接朋友 而是一个陌生类，这样违背了 迪米特法则
class SchoolManagerPlus {
    /** 返回学校总部的员工 */
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<Employee>();

        for (int i = 0; i < 5; i++) { //这里我们增加了5个员工到 employeeList
            Employee emp = new Employee();
            emp.setId("学校总部员工id= " + i);
            employeeList.add(emp);
        }
        return employeeList;
    }

    /** 该方法完成输出学校总部和学院员工信息(id) */
    void printAllEmployee(CollegeManagerPlus collegeManagerPlus) {

        //分析问题
        //1. 将输出学院的员工方法，封装到CollegeManager
        collegeManagerPlus.printEmployee();

        //获取到学校总部员工
        List<Employee> employeeList = this.getAllEmployee();
        System.out.println("------------学校总部员工------------");
        for (Employee e : employeeList) {
            System.out.println(e.getId());
        }
    }
}

