package 海尔2022年笔试题;

/**
 *
 */
public class Test1 {
    static int[][] mayarray = new int[5][5];

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    mayarray[i][j] = 1;
                } else {
                    mayarray[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("int[%d][%d] = %d \t", i, j , mayarray[i][j]);

            }
            System.out.println();
        }

    }
}
/**
 * 问题：
 * 以下代码运行后数组 mayarray 的元素结果为
 * 选：
 *  1 0 0
 *  0 1 0
 *  0 0 1
 *  关键点  i行 j列。   i=j=0,1,2时 为1 。也就是说
 */
