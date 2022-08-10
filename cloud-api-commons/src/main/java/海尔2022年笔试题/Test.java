package 海尔2022年笔试题;

/**
 *
 */
class Pixel{
}

class ColoredPixel extends Pixel {

}
public class Test {
    public static void main(String[] args) {
        ColoredPixel[] cpa = new ColoredPixel[10];
        Pixel[] pa = cpa;
        System.out.println(pa[1] == null);

        try {
            pa[0] = new Pixel();
        } catch (ArrayStoreException e) {
            System.out.println(e);
        }
    }
}
/**
 * 问题，以上代码运行后结果是什么？
 * 答案：
 * true
 * java.lang.ArrayStoreException:
 */
