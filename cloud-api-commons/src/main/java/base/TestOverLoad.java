package base;

/**
 * 测试重载
 */
public class TestOverLoad extends CalcUtil{

    public String print(String result) {
        return "TestOverLoad";
    }

    public static void main(String[] args) {
        TestOverLoad testOverLoad = new TestOverLoad();
        System.out.println(testOverLoad.print(""));
    }
}
