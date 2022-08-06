package DataStructures.src.com.atguigu.sparsearray;

/**
 *
 */

public class SparseArray2 {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] array = new int[11][11];
        array[1][1] = 1;
        array[2][2] = 2;

        for (int[] row : array) {
            // System.out.println(Arrays.toString(row));
            for (int data :
                    row) {
                System.out.printf("%d\t", data);

            }
            System.out.println();
        }

        // 输出原始的二维数组
        int count = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
        int[][] sparseArray = new int[count+1][3]; //因为稀疏数组第一行是统计。所以稀疏数组的行一定是有效数据加上第一行，总共count+1行
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = count;

        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                // sparseArray[?][j] = 1;
            }
        }


        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数


        // 2. 创建对应的稀疏数组
        // 给稀疏数组赋值

        // 遍历二维数组，将非0的值存放到 sparseArr中
         //count 用于记录是第几个非0数据


        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");

        System.out.println();

        //将稀疏数组 --》 恢复成 原始的二维数组
		/*
		 *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
			2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组


        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可


        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");


    }

}


