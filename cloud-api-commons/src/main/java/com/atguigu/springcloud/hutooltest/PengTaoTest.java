package com.atguigu.springcloud.hutooltest;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 测试类
 * @author pengtao
 */
public class PengTaoTest {
    @Test
    public void test1() {

    }

    private String testKebiancanshu(String param1, String... params2) {
        return null;
    }
    // 无法编译：Vararg parameter must be the last in the list 可变参数必须放在参数列表的最后一个
    /*private String testKebiancanshu(String param1, String... params2, String... param3) {
        return null;
    }*/

    @Test
    public void test3() {
        double num23 = Math.pow(2, 7); // byte
        System.out.println(num23);
        double num26 = Math.pow(2, 15);// short
        System.out.println(num26);
    }

    int num;
    short s;
    double d;
    float f;
    long l;
    char c;
    byte b;
    boolean b1;

    @Test
    public void basicDataType() {

        System.out.println(num); // int 默认 0
        System.out.println(s); // short 默认 0
        System.out.println(d); // double 默认 0.0
        System.out.println(f); // float 默认 0.0
        System.out.println(l); // long 默认 0
        System.out.println(c); // char 默认 '' (空字符)
        System.out.println(b); // byte 默认 0
        System.out.println(b1); // boolean 默认 false
    }

    @Test
    public void testHashCode() {
        Object o = new Object();
        int i = o.hashCode();
        System.out.println(i); // 231685785
        String str = new String("test");
        System.out.println(str.hashCode());
        System.out.println(str.hashCode());
        String str2 = "test2";
        System.out.println(str2.hashCode());
    }

    @Test
    public void testStringIntern() {
        // intern v.拘留，扣押(战时或政治原因) n实习生

        // 在堆中创建字符串对象”Java“
        // 将字符串对象”Java“的引用保存在字符串常量池中
        String s1 = "Java";
        // 直接返回字符串常量池中字符串对象”Java“对应的引用
        String s2 = s1.intern();
        // 会在堆中在单独创建一个字符串对象
        String s3 = new String("Java");
        // 直接返回字符串常量池中字符串对象”Java“对应的引用
        String s4 = s3.intern();
        // s1 和 s2 指向的是堆中的同一个对象
        System.out.println(s1 == s2); // true
        // s3 和 s4 指向的是堆中不同的对象
        System.out.println(s3 == s4); // false
        // s1 和 s4 指向的是堆中不同的对象
        System.out.println(s1 == s4); //true
    }

    @Test
    public void testScanner() {
        InputStream in = System.in;

        Scanner scanner = new Scanner(System.in);

    }
}
