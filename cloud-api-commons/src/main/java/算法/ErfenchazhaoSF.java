package 算法;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分查找算法
 */
public class ErfenchazhaoSF {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入你想查找的 升序的数组的元素，空格分隔");
        String nextLine = scanner.nextLine();
        String[] s = nextLine.split(" ");
        // System.out.println("s[]: " + Arrays.toString(s));

        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            // System.out.println("s[i]: " + s[i].trim());
            ints[i] = Integer.parseInt(s[i].trim());
        }
        System.out.println("s[]: " + Arrays.toString(ints));
        System.out.println("输入你想查找的数");
        int target = scanner.nextInt();

        int result = binarySearch(ints, target);
        System.out.println("返回下标" + result);

    }

    public static int binarySearch(int[] arr, int target) {
        // {1, 3, 5, 7, 11}         0  2  4
        // {1, 3, 5, 7}         0  1  3

        int left = 0;
        int right = arr.length-1;
        int mid;

        while (left <= right) {
            System.out.println("right: " + right);
            mid = (left+right) / 2;
            System.out.println("mid: " + mid);

            if (target == arr[mid]) {
                return mid;
            } else if(target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1 ;
                System.out.println("left:" + left);
            }
        }
        return -1;
    }

}
