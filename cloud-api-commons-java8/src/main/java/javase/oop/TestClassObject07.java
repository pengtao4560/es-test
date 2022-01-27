package javase.oop;
import java.util.HashMap;
import java.util.Map;
/**
 * 类变量的初始化顺序问题?
 * 看类变量的定义顺序也就是书写顺序
 */
class ClassAB{
	static Map<String,Object> map=new HashMap<String,Object>();
	static ClassAB instance=new ClassAB();
	public ClassAB() {
		map.put("A", 100);
		map.put("B", 200);
	}
}
public class TestClassObject07 {
    public static void main(String[] args) {
    	System.out.println(ClassAB.instance);
	}
}
