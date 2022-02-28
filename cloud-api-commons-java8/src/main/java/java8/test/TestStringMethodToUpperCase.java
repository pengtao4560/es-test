package java8.test;

import org.junit.Test;

/**
 * 测试string.toUpperCase()
 */
public class TestStringMethodToUpperCase {
    @Test
    public void testStringMethodToUpperCase() {
        String str = "hello world";
        System.out.println(str.toUpperCase()); // HELLO WORLD
        String str1 = "hello_world";
        System.out.println(str1.toUpperCase()); // HELLO_WORLD

    }
}
