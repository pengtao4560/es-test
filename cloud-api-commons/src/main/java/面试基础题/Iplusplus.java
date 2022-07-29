package 面试基础题;

/**
 * i++和 ++i
 * <a href="https://blog.csdn.net/qq_34471736/article/details/54599901?spm=1001.2101.3001.6650.16&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-16-54599901-blog-120330685.pc_relevant_multi_platform_whitelistv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-16-54599901-blog-120330685.pc_relevant_multi_platform_whitelistv1&utm_relevant_index=24">Java中i++和++i的区别</a>
 */
public class Iplusplus {

    public static void main(String[] args) {
        /*
         * i++ 先赋值在运算,例如 a=i++,先赋值a=i,后运算i=i+1,所以结果是a==1
         * ++i 先运算在赋值,例如 a=++i,先运算i=i+1,后赋值a=i,所以结果是a==2
         * */
        int i = 1;
        int a = i++;
        System.out.println("a = i++ 之后 a的结果为： " + a);
        // a=i++ 之后 a的结果为： 1

        i = 1;
        int b = ++i;
        System.out.println("b = ++i 之后 b的结果为： " + b);
        // a=++i 之后 a的结果为： 2

        // 在 i = 1 的时候 执行 int a = i++; 打印 a的值为1
        // 在 i = 1 的时候 执行 int b = ++i; 打印 b的值为2

        // System.out.println(testIplusplus());
    }

/*    static int testIplusplus() {
        int result = 0;

        int i = 1;

        for (int j = 1; j <= 99; j++) {
            j = i ++;
        }

        System.out.println(i);

        return result;
    }*/
}
