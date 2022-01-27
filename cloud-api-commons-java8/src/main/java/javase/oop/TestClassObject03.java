package javase.oop;
class ClassA{
	static {
		System.out.println("类加载时静态代码块执行了打印本句话：ClassA.static{}");
	}
}
//-XX:+TraceClassLoading
//public class TestClassObject03 {
//	public static void main(String[] args)throws Exception {
//	  //需求:使用ClassLoader加载ClassA
//	  //loadClass01();
//	  //loadClass02();
//	  //loadClass03();
//	  //new ClassA();
//	}
//	private static void loadClass03()throws Exception {
//		Class.forName("com.cy.java.oop.ClassA",
//				      true,//initialize 为true时会执行静态代码块
//				      ClassLoader.getSystemClassLoader());//loader
//	}
//	private static void loadClass02()throws Exception {
//		Class.forName("com.cy.java.oop.ClassA");
//	}
//	//alt+shift+m
//	private static void loadClass01() throws ClassNotFoundException {
//		//1.获取类的加载器对象
//		ClassLoader loader=
//		ClassLoader.getSystemClassLoader();
//		//System.out.println(loader.getClass());
//		//2.加载类(将类读到内存中)
//		loader.loadClass("com.cy.java.oop.ClassA");
//      //如何证明如上类被加载了?(使用-XX:+TraceClassLoading参数)
//	}
//}

/*使用类加载器来加载类时不会执行静态代码块：
* 结论：类加载时不一定执行静态代码块
* */
public class TestClassObject03{
    public static void main(String[] args) throws ClassNotFoundException {
      classLoader01();
        classLoader02();
    }

    private static void classLoader02() throws ClassNotFoundException {
        ClassA classA = new ClassA();
        System.out.println("分割线1------");
        Class.forName("com.cy.java.oop.ClassA");//该方法：默认类加载时执行静态代码块
        System.out.println("分割线2--------");
        //第二个参数为false时不会加载静态代码块，为true时执行静态代码块
        Class.forName("com.cy.java.oop.ClassA", true, ClassLoader.getSystemClassLoader());
        System.out.println("分割线3----------------");
    }

    private static void classLoader01() throws ClassNotFoundException {
        //需求：使用ClassLoader加载ClassA
        //1.获取类加载器对象(
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        //2.加载类，也就是将类读到内存
        Class<?> aClass = loader.loadClass("com.cy.java.oop.ClassA");
        //如何证明如上类被加载了?(使用-XX:+TraceClassLoading参数)
        System.out.println("方法1：-----"+aClass);
        System.out.println();
        //Trace跟踪
    }

}
