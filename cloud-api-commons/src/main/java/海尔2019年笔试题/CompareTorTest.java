package 海尔2019年笔试题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Person implements Comparable<Person>{
    private String name;
    private int age;
    private int money;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public Person(String name, int age, int money) {
        super();
        this.name = name;
        this.age = age;
        this.money = money;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", money=" + money + "]";
    }
    /** *  * 这里是通过“person的年龄”进行比较的 * 注意：在做自然排序方法重写的时候，一定先判断主要条件，再判断次要条件 *   *  *  */
    @Override
    public int compareTo(Person o) {//重写的方法
        System.out.println("this.age="+this.age+"：o.age="+o.age+"="+(this.age-o.age));	return this.age-o.age;//升序
    }
}

/**
 * 排序
 */
public class CompareTorTest {

    public static void main(String[] args) {
        System.out.println("输入：");
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] num1 = new int[m];
        int[] num2 = new int[n];
        // 换成其他数据类型也一样，其他数值类型就修改int跟nextInt就可以了，
        //String就把nextInt()换成next()
        for(int i = 0; i < m; i ++) {
            num1[i] = sc.nextInt();  // 一个一个读取
        }
        for(int i = 0; i < n; i ++) {
            num2[i] = sc.nextInt();
        }
        System.out.println("输出：");
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));

    }
}
