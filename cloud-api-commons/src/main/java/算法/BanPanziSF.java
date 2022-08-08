package 算法;

import java.util.Scanner;

/**
 * 分治算法案例： 搬盘子， 汉诺塔问题
 * 分治算法最佳实践-汉诺塔
 *
 * 汉诺塔的传说
 * 汉诺塔：汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，
 * 在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘。
 *
 * 假如每秒钟一次，共需多长时间呢？移完这些金片需要5845.54亿年以上，太阳系的预期寿命据说也就是数百亿年
 * 。真的过了5845.54亿年，地球上的一切生命，连同梵塔、庙宇等，都早已经灰飞烟灭。
 */
public class BanPanziSF {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输出汉诺塔盘子的数量");
        int num = scanner.nextInt();
        System.out.println("输入汉诺塔盘子的3个柱子的名字,原柱子，中间柱子，目标柱子。以逗号分隔。例如 A,B,C");
        Scanner scanner2 = new Scanner(System.in);
        String s = scanner2.nextLine();
        System.out.println("s: " + s);
        String[] split = s.split(",");
        String old = split[0];
        String mid = split[1];
        String target = split[2];

        hanuota(num, old, mid, target);
    }

    /**
         1
         2
         3
        -A-         -B-         -C-
        A:old       B:mid     C:target
     * @param num
     * @param old  原先放盘子的柱子
     * @param mid  借助的柱子
     * @param target  要移动的柱子
     */
    public static void hanuota(int num, String old, String mid, String target) {
        // 思路，把最下面一个盘子看成一个整体，其他盘子看成一个整体。
        if (num == 1) {
            System.out.printf("第" + num + "个盘子 %s -> %s", old, target);
            System.out.println();
        } else {
            // 第一步，把其他 num-1 个盘子移动到 mid，借助 target
            hanuota(num-1, old, target , mid);
            // 第二步，把最下面一个盘子移动到 target
            System.out.printf("第" + num + "个盘子 %s -> %s", old, target);
            System.out.println();

            // 第三步不是循环，而是 把 num-1 (除了最后一个之外的所有的盘子) 从 柱子 mid 移动到柱子 target 借助 old
            hanuota(num-1, mid, old, target);
        }

    }
}
