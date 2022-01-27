package javase.oop;
/**JAVA中的类加载器*/
public class TestClassObject04 {
		public static void main(String[] args) {
		ClassLoader c1=
		ClassLoader.getSystemClassLoader();
		//1)AppClassLoader (加载我们自己写的类)
		System.out.println(c1.getClass());
		//2)ExtClassLoader (JRE/lib/ext)
		ClassLoader c2=c1.getParent();
		System.out.println(c2.getClass());
		//3)BootstrapClassLoader (Jre/lib)
		ClassLoader c3=c2.getParent();
		System.out.println(c3);

	}
}
