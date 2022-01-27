package javase.generic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TestGeneric06 {
	static void doMethod(Integer[] array) {}
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		list.add(100);
		list.add(200);
	    //将如上list集合转换为一个整数数组并输出数组中内容
		//Object[] array=list.toArray();
		Integer[] array={};
		Integer[] values=list.toArray(array);
		System.out.println(Arrays.toString(values));
	}
}
