package 算法;

import java.util.Arrays;


/**`+
 * 
 * 公交站 问题 克鲁斯卡尔算法
 *
 *           A          B          C             D          E           F         G
 * A         0	        12	       999	       999	       999	        16	        14
 * B        12	         0	        10	       999	       999	         7	       999
 * C       999	        10	         0	         3	         5	         6	       999
 * D       999	       999	         3	         0	         4	       999	       999
 * E       999	       999	         5	         4	         0	         2	         8
 * F       16	         7	         6	       999	         2	         0	         9
 * G       14	       999	       999	       999	         8	         9	         0
 */
public class GongJiaoZhanSF {
    /*** 边的个数 */
    private int edgeNum;
    /** 顶点数组 */
    private char[] pointsChar;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    /** 使用 INF 表示两个顶点不能连通 */
    private static final int INF = 999;

    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);
        System.out.println("输入公交车站点，空格分隔，例如 A B C D E F G");
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");*/
        System.out.println("TODO"); //TODO
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix =  {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        // 实例化
        GongJiaoZhanSF gongJiaoZhanSF = new GongJiaoZhanSF(vertexs, matrix);
        gongJiaoZhanSF.print();

        System.out.println();
        EData[] edges = gongJiaoZhanSF.getEdges();
        System.out.println("没有排序edges: " + Arrays.toString(edges));
        gongJiaoZhanSF.sortEdges(edges);
        System.out.println("没有排序edges: " + Arrays.toString(edges));

        System.out.println();
        System.out.println();
        gongJiaoZhanSF.kruskal();


    }

    // 构造器
    public GongJiaoZhanSF(char[] pointsChar, int[][] matrix) {
        // 初始化顶点数和边的个数
        int vlen = pointsChar.length; //顶点数就是 char数组的长度

        this.pointsChar =new char[vlen];

/*        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }*/
        this.pointsChar = pointsChar.clone();

        // 初始化边
        this.matrix = new int[vlen][vlen]; // 邻接矩阵的行和列
        this.matrix = matrix.clone();

/*        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }*/

        // 统计有校的边有多少条
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为：\n");
        String s = "         A          B          C             D          E           F         G";
        System.out.println(s);
        for (int i = 0; i < pointsChar.length; i++) {
            for (int j = 0; j < pointsChar.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理，冒泡
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j+1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch 传入的顶点的值。 比如 'A', 'B'
     * @return 返回 ch 顶点对应的下标，如果找不到，返回 -1；
     */
    private int getPosition(char ch) {
        for (int i = 0; i < pointsChar.length; i++) {
            if (pointsChar[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 功能：获取图中边， 放到EData[] 数组中，后面我们需要遍历该数组
     * 是通过 matrix 邻接矩阵获取
     * EData[] 形式 [['A', 'B', 12], ['B','F',7],...]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];

        for (int i = 0; i < pointsChar.length; i++) {
            for (int j = i+1; j < pointsChar.length ; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(pointsChar[i], pointsChar[j], matrix[i][j]);
                }
            }

        }
        System.out.println(Arrays.toString(edges));
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于 后续判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点是哪个。 ends 数组是在遍历过程中，逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 克鲁斯卡尔算法求 公交站问题最小连接节点
     */
    public void kruskal() {
        int index = 0;// 表示最后结果数组的索引
        int[] ends = new int[edgeNum];  //用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        // 创建结果数组，保存最后的最小生成树
        EData[] results = new EData[edgeNum];

        // 获取条件图中所有的边的集合， 一共有12条边（两点连线）。
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "\n共" + edges.length + "条边");

        // 按照边的权值大小排序
        sortEdges(edges);

        // 便利edges 数组， 将边添加到最小生成树中时，判断是准备加入的边是否形成了回路
        // 如果没有形成回路就加入到rets中，否则不能加入

        for (int i = 0; i < edgeNum; i++) {
            // 获取到第 i 条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            // 获取到第 i 条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            // 获取 p1顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);

            // 获取 p2顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);

            //判断是否构成回路  也就是 m=n
            if (m != n) { // 不构成回路，才能够加入
                ends[m] = n; // 设置m 在  “已有最小生成树的终点”
                results[index++] =edges[i]; // 有一条边加入到rets数组
            }
        }

        // 统计并打印最小生成树
        System.out.println("答案： \n\n\n");
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
        System.out.println("最小生成树为=" + Arrays.toString(results));
    }
}

class EData {
    /**
     * 边的起点
     */
    char start;
    /**
     * 边的终点
     */
    char end;

    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
