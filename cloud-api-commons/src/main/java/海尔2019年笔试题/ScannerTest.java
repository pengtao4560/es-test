package 海尔2019年笔试题;

import java.util.Scanner;

/**
 *
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);   //初始化
        int n = scan.nextInt();  //输入n
        int[] a = new int[n];    //定义数组a
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        //输入a数组各个元素
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
        //输出各个元素
    }
}
