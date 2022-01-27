package javase.cache;import java.io.Serializable;
import java.util.Date;

/**
 *   此cache支持序列化
 * 1)存对象时需要将对象序列化
 * 2)取对象时需要从cache反序列化(相对于是对象的拷贝)
 */
public class SerializedCache<K,V> implements Cache<K, V> {
	private Cache<K,byte[]> cache;
	public SerializedCache(Cache<K,byte[]> cache) {
		this.cache=cache;
	}
	@Override
	public void putObject(K key, V value) {
		byte[] array=KryoUtil.serialization((Serializable)value);
		cache.putObject(key,array);
	}
	@SuppressWarnings("unchecked")
	@Override
	public V getObject(K key) {
		byte[] array=cache.getObject(key);
		if(array==null||array.length==0)
		return null;
		return (V)KryoUtil.deserialization(array,Object.class);
	}
	public static void main(String[] args) {
		FifoCache<String,byte[]> fCache=
		new FifoCache<String,byte[]>(3);
	    SerializedCache<String,Object> sCache=
	    new SerializedCache<String, Object>(fCache);
	    sCache.putObject("d1",new Date());
	    Date date2=new Date();
	    sCache.putObject("d2",date2);
	    sCache.putObject("d3",new Date());
	    sCache.putObject("d4",new Date());
	    Object d1=sCache.getObject("d1");
	    System.out.println(d1);
	    Object d2=sCache.getObject("d2");
	    System.out.println(d2==date2);
	}
}
