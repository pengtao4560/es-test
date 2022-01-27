package javase.memory;

//java.lang.StackOverflowError
public class TestStackMemory01 {
	static void doMethod01() {
		doMethod01();
	}
	public static void doMethod02() {
		doMethod03();
	}
	public static void doMethod03() {
		Thread t=Thread.currentThread();
		StackTraceElement[] st=
		t.getStackTrace();
		for(StackTraceElement s:st) {
			System.out.println(s.getMethodName());
		}
	}
	public static void main(String[] args) {
		//doMethod01();
		doMethod02();
	}
}
