package javase.oop;
/**
 * 隐式类加载触发的时机?
 * 1)访问类中没有使用final修饰的类成员时
 * 2)访问类中的使用了final修饰的非string和非8种基本数据类型
 * 检测:-XX:+TraceClassLoading
 */
class ClassB___________________{
/*	原因static final 修饰的String以及8种基本类型在编译时有优化，
	，通过类名直接方法时不会触发类的加载*/
	static final String LOCK="LOCK";
	static final int ORDER=Integer.MAX_VALUE;
	static final Integer NUMBER=100;
	static int count=100;
	public static void show() {}
}
public class TestClassObject05 {
	public static void main(String[] args) {
//		ClassB___________________ c1;//不会触发类加载

		/*以下都会触发类加载*/
//		int count=ClassB___________________ .count;
//		ClassB___________________ .show();
//		new ClassB___________________ ();
//		Integer number=ClassB___________________ .NUMBER;
		ClassB___________________.show();

		System.out.println("---------分割线");
		/*以下都不会触发类加载*/
		String s1=ClassB___________________ .LOCK;
		int order=ClassB___________________ .ORDER;

	}
}
