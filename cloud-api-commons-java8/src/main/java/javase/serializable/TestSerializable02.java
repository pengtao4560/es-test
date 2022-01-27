package javase.serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
/**序列化cache的实现?(参考MyBatis中SerializedCache)
 * 为什么写到缓存中的对象要序列化
 * 原因:获取时能拿到对象的拷贝,以保证缓存中对象的线程安全
 * */
class SerializedCache{
	private HashMap<String,Object> cache= new HashMap<String,Object>();
	public void putObject(String key,Object value) {
		try {
		cache.put(key, serializable(value));//把对象序列化以后再放进去
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
	public Object getObject(String key) {
		byte[] array=(byte[])cache.get(key);
		try {
		return deserialize(array);
		}catch(Exception e) {
	    e.printStackTrace();
	    return null;
		}
	}
	//序列化就是把对象转化为字节数组所以这里是byte[]类型作为返回值类型。
	public byte[] serializable(Object obj)throws Exception {
		//构建字节数组输出流(内置一个可扩容的字节数组)
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//构建序列化对象流
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.close();
		return bos.toByteArray();
	}
	public Object deserialize(byte[] array)throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(array);
		ObjectInputStream in = new ObjectInputStream(bis);
		Object obj = in.readObject();
		in.close();
		return obj;
	}
}
public class TestSerializable02 {
	public static void main(String[] args) {
		Message msg=new Message();
		msg.setId(200);
		msg.setContent("hello china");
		SerializedCache sc=
		new SerializedCache();
		sc.putObject("msg", msg);
		Object obj=sc.getObject("msg");
		System.out.println(obj);
		System.out.println(msg==obj);

	}
}
