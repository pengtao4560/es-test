package compare;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Person implements Comparable<Person>{
    public int age;    //年龄
    public String name;  //姓名

    //get、set方法
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Person(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }
    //实现Comparable接口必须实现compareTo方法
    public  int compareTo(Person o) {
        if(this.age==o.age&&this.name==o.name){
            return 0;
        }else if(this.age>o.age){
            System.out.println("this.age:"+this.age+"o.age:"+o.age);
            return 1;
        }else{
            return -1;
        }
    }
    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }


}


public class TestCompare {
    @Test
    public void test1(){
        List<Person> list=new ArrayList<Person>();
        Person test1=new Person(66,"李四");
        Person test2=new Person(29,"王五");
        Person test3=new Person(13,"赵六");
        Person test4=new Person(20,"钱三");
        Person test5=new Person(-20,"钱三");
        list.add(test4);
        list.add(test3);
        list.add(test2);
        list.add(test1);
        list.add(test5);
        Collections.sort(list);
        for(Object s:list){
            System.out.println(s);
        }
    }
}
