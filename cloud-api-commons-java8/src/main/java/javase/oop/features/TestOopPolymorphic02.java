package javase.oop.features;
public class TestOopPolymorphic02 {
//	static int doSum(int a,int b) {
//		return a+b;
//	}
//	static int doSum(int a,int b,int c) {
//		return a+b+c;
//	}
//	static int doSum(int[] array) {
//		int sum=0;
//		for(int i=0;i<array.length;i++) {
//			sum+=array[i];
//		}
//		return sum;
//	}
	//可变参数(JDK1.5):可以将其理解为特殊数组
	//可变参数只能作为最后一个参数出现
	//目的：为了简化同参数类型的一些重载方法的编写
	static int doSum(int... array) {
		int sum=0;
		for(int i:array) {
			sum+=i;
		}
		return sum;
	}
	public static void main(String[] args) {
		int sum=doSum(10,20);
		sum=doSum(10,20,30);
		int array[]= {1,2,3,4};
		sum=doSum(array);
	}
}
