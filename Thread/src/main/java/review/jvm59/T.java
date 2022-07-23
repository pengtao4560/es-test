package review.jvm59;

/**
 * cmd 窗口下运行 javac T.java 为什么不能带着 package（因为那样 javac T.java 编译后默认不在和 T.java同意目录下）
 */
public class T {

    public static void main(String[] args) {
        int a = 100;
        int b = 200;
        int retValue =  a + b;
        System.out.println("**********retValue: " + retValue);
    }
}
