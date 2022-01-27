package javase.cache;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/**
 * ThreadLocal 提供了一种线程绑定机制,能够
 *    将某个对象绑定到当前线程,也可以从当前线程
 *    获取绑定的对象,常用方法:
 * 1)get()获取当前线程绑定的对象
 * 2)set(value) 绑定对象到当前线程
 * 3)remove()
 */
public class KryoUtil {
	//此对象线程不安全
	//static Kryo kryo = new Kryo();

	private static final ThreadLocal<Kryo> td = new ThreadLocal<Kryo>() {
		//外界通过ThreadLocal的get方法获取 //绑定的对象时,假如当前线程没有,则 //创建一份并绑定到当前线程
		@Override
		protected Kryo initialValue() {
			System.out.println("create kryo");
			return new Kryo();
			}
		};

	public static byte[] serialization(Serializable obj){
		//Kryo kryo=td.get();
		td.get().register(obj.getClass());
		ByteArrayOutputStream bos=
		new ByteArrayOutputStream();
		Output output=new Output(bos);
		td.get().writeObject(output, obj);
		output.close();
		return bos.toByteArray();
	}
	public static <T>T deserialization(byte[] array,Class<T> cls){
		td.get().register(cls);
		ByteArrayInputStream inputStream=
		new ByteArrayInputStream(array);
		Input input=new Input(inputStream);
		T t=td.get().readObject(input,cls);
		input.close();
		return t;
	}
}
