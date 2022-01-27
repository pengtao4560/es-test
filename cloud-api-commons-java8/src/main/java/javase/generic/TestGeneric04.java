package javase.generic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ContainerUtils{
	//泛型方法
	//1)静态方法假如有泛型肯定是泛型方法
	//2)泛型类和泛型接口不作用于静态方法
	//3)泛型方法一定是静态方法吗?不是
	public static <T>void sort(List<T> list) {}
	public static <T>List<T> add(List<T> list,T... ts){
		for(T t:ts) {
			list.add(t);
		}
		return list;
	}
	//限定通配符(一般应用在方法参数或返回值类型)
	//限定泛型上界
	public static void doPrint(List<? extends Number> list) {}
	//限定泛型下届
	public static void doPrint(Set<? super Integer> list) {}
}
public class TestGeneric04 {
    public static void main(String[] args) {
		 List<String> list1=new ArrayList<String>();
		 list1.add("B");
		 list1.add("A");
		 List<Integer> list2=new ArrayList<Integer>();
		 list2.add(100);
		 list2.add(200);
		 List<Long> list3=new ArrayList<Long>();
		 list3.add(100L);
		 list3.add(200L);
		 ContainerUtils.sort(list1);
		 ContainerUtils.sort(list2);
		 ContainerUtils.doPrint(list3);

		List<Integer> list=
		ContainerUtils.add(list2,300,400,500);
		System.out.println(list);

	}
}
