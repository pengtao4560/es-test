package compare;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class emp {
    public int age;
    public String name;
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
    public emp(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    public emp() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "emp [age=" + age + ", name=" + name + "]";
    }
}
/**
 *
 */
public class TestComparator {

    @Test
    public void test2(){
        List<emp> list=new ArrayList<emp>();
        emp test1=new emp(69,"李四");
        emp test2=new emp(9,"王五");
        emp test3=new emp(28,"赵六");
        emp test4=new emp(20,"钱三");
        list.add(test4);
        list.add(test3);
        list.add(test2);
        list.add(test1);
        Collections.sort(list,new Comparator<emp>(){
            @Override
            public int compare(emp o1, emp o2) {
                if(o1.age==o2.age&&o1.name==o2.name){
                    return 0;
                }else if(o1.age>o2.age){
                    return 1;
                }else{
                    return -1;
                }
            }
        });

        for(Object s:list){
            System.out.println(s);
        }
    }
}
