package javase.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pt
 * @date 2019/12/7 - 21:16
 */
public class TestGeneric {
    public static void main(String[] args) {
//        incompatible type 类型不兼容
//        List<String> list = new ArrayList<Object>();
//        List<Object> list = new ArrayList<String>();
        List<?> list = new ArrayList<String>();
        List<? extends Object> list2 = new ArrayList<Integer>();

    }
}
