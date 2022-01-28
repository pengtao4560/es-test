package javase.effectivejava.c21;

/**
 * @description: DESIGN INTERFACES FOR POSTERITY【为后代设计接口】
 * @author: peng tao
 * @create: 2022-01-28 15:15
 */
public class testXiaHuaXian {
    /* */
    public static final int num1 = 100_200_300;
    public static final double num2 = 1.00_200_300;
    public static final double num3 = 100_2.100_300;

    public static void main(String[] args) {
        System.out.println(num1); //100200300
        System.out.println(num2); //1.002003
        System.out.println(num3); //1002.1003
    }
}
