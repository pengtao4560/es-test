package javase.effectivejava.c26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型类型 不要使用原始类型
 * 泛型类型就是泛型类或者泛型接口  List<E> 它的原始类型就是不带泛型，也就是  List
 * @author: peng tao
 * @create: 2022-01-29 10:15
 */
public class WithGeneric {
    /**下面演示为什么泛型不要使用原始类型
    * @description: 只有main方法执行了。  我们的方法被调用了之后才能发现编译错误
    * */
    public static void main(String[] args) {
        Collection strList = initStrList();
        // 模拟其他方法对list处理
        strList.add(10);
        for (Object obj : strList) {
            String str = (String) obj;
            str.contains("西游记");
        }

    }
    /** 初始化一个字符串集合*/
    private static List initStrList() {
        /* strList 是一个 字符串的List集合*/
        List strList = new ArrayList<>();
        strList.add("西游记唐僧");
        strList.add("西游记孙悟空");
        return strList;
    }
}
