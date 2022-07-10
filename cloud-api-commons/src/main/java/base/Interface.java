package base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface Interface {
    String name = null;
    List<Object> list = new ArrayList();

    default void m1() {

    }

    default void m2(){

    }

    static void m3() {
        System.out.println("m3");
    }

    public static void main(String[] args) {
        Interface.m3();
    }
}
