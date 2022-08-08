package 算法;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 测试java  Scanner
 */
public class TestScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入地区以空格间隔");
        String s1 = scanner.nextLine();
        System.out.println(s1);
        String s2 = scanner.nextLine();
        System.out.println(s2);
        String[] sArr = s1.split(" ");
        String[] sArr2 = s2.split(" ");

        HashSet<Object> set = new HashSet<>();
        for (String s : sArr) {
            set.add(s);
        }
        System.out.println(set);

        HashSet<Object> set2 = new HashSet<>();
        for (String s : sArr2) {
            set2.add(s);
        }
        System.out.println(set2);
    }
}
