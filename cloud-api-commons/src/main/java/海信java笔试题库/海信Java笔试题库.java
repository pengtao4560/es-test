package 海信java笔试题库;

import com.beust.ah.A;
import org.junit.Test;

/**
 * 海信java
 */
public  class 海信Java笔试题库 {

    @Test
    public void test1() {
    /*
    1.以下哪种手段不可以防御CSRF漏洞？选 D
    A 验证HTTP Referer字段
    B 使用验证码
    C.在请求地址中添加token并验证
    D.     D 过滤用户输入的内容*/
    }

    @Test
    public void test2() {
        /**
         * 2..在java中下列关于自动类型转换说法正确的是  A
         * A. 基本数据类型和String相加结果一定是字符串型
         * B. char类型和int类型相加结果一定是字符
         * C. double类型可以自动转换为int
         * D. char+int+double+"结来一定是double:
         */
    }

/*    3.下列哪一种叙述是正确的: D
    A abstract 修饰符可修饰字段、方法和类   PS: abstract不可以修饰字段
    B.抽象方法的 body 部分必须用一对大括号{} 包住
    C.声明抽象方法，大搭号可有可无
    D.声明抽忽方法不可写出大括号
    */

    // public abstract void m1();
    // public abstract String s1; // Modifier 'abstract' not allowed here

    @Test
    public void test4() {
        System.out.println(foo(5));
        // 5
    }
    int foo(int n){
        if (n < 2) return n;
        return foo(n-1) + foo(n-2);
    }

    /**
     5,下列思种说法是正确的 D
     实例方法可直接调用超类的实例方法
     实例方法可直接调用超类的类方法
     实例方法可百接润用其他类的实列方法
     实例方法可直接调用本类的类方法
     */
    static int m1() {
        return 1;
    }
}
