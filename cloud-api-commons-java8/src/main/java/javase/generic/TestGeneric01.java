package javase.generic;
interface Container<T>{//泛型接口
	 void add(T t);
	 T get(int i);
	 int size();
}
abstract class AbsContainer<T>
         implements Container<T>{//泛型类
	protected int size;
	public int size() {
		return size;
	}
}
class ArrayContainer<T> extends AbsContainer<T>{
	private Object[] array;
	public ArrayContainer() {
		this.array=new Object[16];
	}
	@Override
	public void add(T t) {}
	@Override
	public T get(int i) {
		return null;
	}
}
public class TestGeneric01 {
   public static void main(String[] args) {
	   //泛型只能应用对象类型
	   Container<Integer> c=
	   new ArrayContainer<Integer>();
	   c.add(100);
	   c.size();
   }
}








