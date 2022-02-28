package com.atguigu.flyweight;

/**
 * 享元模式 在jdk-Integer的应用
 *  @see java.lang.Integer.IntegerCache
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
public class FlwWeightOfInteger {

    public static void main(String[] args) {


        //

        /*
        如果Integer.valueOf(x) x在 -127-128之间。就是使用享元模式返回。如果不在该范围，则仍然new新对象
        小结:
        1。在value0F 方法中，先判断值是否在IntegerCache 中，如果不在，就创建新的Integer(new)，否则，就直接从缓存池返回
        2. valueOf方法,就使用到享元模式
        3. 如果使用valueOf 方法得到一个Integer实例， 范围在 -128到127之间，执行速度比new Integer对象快
        */
        Integer x = Integer.valueOf(127);

        Integer y = new Integer(127);

        Integer z = Integer.valueOf(127);

        Integer w = new Integer(127);

        System.out.println(x.equals(y)); //true
        System.out.println(x == y); // false
        System.out.println(x == z); // true
        System.out.println(w == x); // false
        System.out.println(w == y); // false

        /**
         *     public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *     }
         * */

    }
}
