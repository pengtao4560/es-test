package javase.oop;
/*字节码对象的获取方式?(常用方式有三种)
a) 类名.class
b) Class.forName(“包名.类名”)
c) 类的实例对象.getClass();
*/
public class TestClassObject02 {
	public static void main(String[] args)
			throws Exception{
		Class<Object> c1=Object.class;
							//类的全路径
		Class<?> c2=
				Class.forName("java.lang.Object");
		Object obj=new Object();
		Class<?> c3 = obj.getClass();
		System.out.println(c1==c2);//true
		System.out.println(c2==c3);//true
	}
}
