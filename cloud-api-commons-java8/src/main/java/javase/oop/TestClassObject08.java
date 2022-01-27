package javase.oop;
//-XX:+TraceClassLoading
class C{//JVM
	static final int a=100;//编译时优化
	static final Integer b=200;//无编译时优化
	static int c=300;
	static {System.out.println("C.static{}");}
	int m;
	{//实例代码块：每次构建对象的时候都会执行。
		//而静态代码块只会执行一次
		m=10;
	}
	{//实例代码块(构造实例执行)
		m=10;
	}
	public static void show() {}
}
class D extends C{
	//被动加载:子类通过类名直接访问父类static成员
	static {
		System.out.println("D.static{}");
	}//被动加载静态代码块不会执行.
}
public class TestClassObject08 {
	public static void main(String[] args) {
		//new D();//加载C,D,并初始化
		System.out.println(D.a);//C,D都不加载
		//System.out.println(D.b);//C加载并且初始化,D被加载,但不被初始化
		//System.out.println(D.c);//C加载并且初始化,D被加载,但不被初始化
		//D.show();
		Object o1 = new Object();
		Object o2= new Object();
		System.out.println(o1 == o2);
		System.out.println(o1.equals(o2));
		System.out.println(o1.hashCode() == o2.hashCode());
	}
}
