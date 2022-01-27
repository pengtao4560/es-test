package java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: streamAPI
 * @author: peng tao
 * @create: 2021-11-03 16:59
 */
public class UserService2 {
    @Test
    public void collectionsCopyTest(){
            ArrayList<String> arrayList1 = new ArrayList<>();
            //验证初始化集合元素的个数
            System.out.println(arrayList1.size());//输出的结果是0
            arrayList1.add("哼哼");
            arrayList1.add("小哈");
            arrayList1.add("小黑");
            arrayList1.add("小哼");
            arrayList1.add("武松");
            arrayList1.add("哼哼的博客");


            //定义一个目的集合
            //先不初始化目的集合的容量，会报下标越界的错误
            //ArrayList<String> arrayList3 = new ArrayList<>();
            //Collections.copy(arrayList3, arrayList1);
            //Exception in thread "main" java.lang.IndexOutOfBoundsException: Source does not fit in dest
            //初始化目的集合的容量
            List<String> strings = Arrays.asList(new String[arrayList1.size()]);
            ArrayList<String> arrayList3 = new ArrayList<>(strings);
            Collections.copy(arrayList3, arrayList1);
            System.out.println(arrayList3);
            //输出结果是  [哼哼, 小哈, 小黑, 小哼, 武松, 哼哼的博客]

    }
    public static void main(String[] args) {
        ArrayList<String> arrayList1 = new ArrayList<>();
        //验证初始化集合元素的个数
        System.out.println(arrayList1.size());//输出的结果是0
        arrayList1.add("哼哼");
        arrayList1.add("小哈");
        arrayList1.add("小黑");
        arrayList1.add("小哼");
        arrayList1.add("武松");
        arrayList1.add("哼哼的博客");
        // 声明一个目的集合，不规定集合中元素的个数
        ArrayList<String> arrayList3 = new ArrayList<>();
        //将String类型的空值添加到目的集合中
        Collections.addAll(arrayList3,new String[arrayList1.size()]);
        //复制集合
        Collections.copy(arrayList3, arrayList1);
        System.out.println("复制后的集合的元素是"+arrayList3);

        arrayList1.add("张三");
        //输出结果是 	复制后的集合的元素是[哼哼, 小哈, 小黑, 小哼, 武松, 哼哼的博客]
        System.out.println("复制后的集合的元素是"+arrayList1);
        System.out.println("复制后的集合的元素是"+arrayList3);
    }



}



