package javase.testSe;

/**
 *
 */
public class TestOverload1 extends TestOverload {
    public static void method(String str, String str2, String str3) {
        return;
    }

    public static void main(String[] args) {
        method("1");
        method("1", "2");
        method("1", "2", "3");
    }
}
