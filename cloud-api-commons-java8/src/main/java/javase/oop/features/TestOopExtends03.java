package javase.oop.features;
class A{
	public static void print() {
		System.out.println("A.print");
	}
}
class B extends A{
	public static void print() {
		System.out.println("B.print");
	}
}
public class TestOopExtends03 {
    public static void main(String[] args) {
		A a=new B();
		a.print();  //输出： A.print
	}
}
