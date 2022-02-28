package java8.test;

/**
 * 测试类的内部接口和内部类可以是public
 */
public class TestInterface {
    private Test test;

    @FunctionalInterface
    public interface Interface1 {
        public void test1();
    }

    public class Test {
        private String str;
    }

    public static void main(String[] args) {
        TestInterface testInterface = new TestInterface();
    }

}
