package javase.generic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**泛型类型擦除？*/
public class TestGeneric05 {
	public static void main(String[] args)throws Exception {
		 List<String> list=new ArrayList<String>();
		 list.add("A");
		//list.add(100);
		//list.add(0, 200);
		//?如何使用
		// 反射技术将100写入list集合
		//1.获取反射的入口对象(字节码对象)
		 Class<?> cls=list.getClass();
		//2.基于字节码对象获取类中的add方法对象
		 Method method=
		 cls.getMethod("add", Object.class);
		//3.通过反射执行add方法，将100写入集合
		 method.invoke(list,100);
		//4.输出集合内容
		 System.out.println(list);//[A,100]
		//5.继续向list集合的第0个位置写入200
		 method=cls.getMethod("add",int.class,Object.class);
		 method.invoke(list,0,200);
		 System.out.println(list);//[200,A,100]
		 //获取添加第0个元素
		 method=cls.getMethod("get",int.class);
		 Object obj=method.invoke(list, 0);
		 System.out.println(obj);
	}
}







