package javase.effectivejava.c31;

import javase.effectivejava.c29.MyNewStack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @description: 测试c31
 * @author: peng tao
 * @create: 2022-02-09 14:10
 */
public class TestUserStackPushAll {

    MyNewStack<Number> numberMyNewStack = new MyNewStack<>();
    Iterable<Integer> integers = new ArrayList<>() {
        {
            add(11);
            add(12);
        }
    };

    /**
     * @see MyNewStack#pushAllFailed(Iterable) ;
     * */
    @Test
    public void testPushAll() {

        // 此处编译器报错
        // numberMyNewStack.pushAllFailed(integers);

        // 优化方案
        numberMyNewStack.pushAll(integers);
    }

    @Test
    public void testPopAll() {
        Collection<Object> objects = new ArrayList<>() {
            {
                add(11);
                add("pengtao");
            }
        };
        // 此处编译器报错
        // numberMyNewStack.popAllOld(objects);

        // 优化方案
        numberMyNewStack.popAll(objects);
    }
}
