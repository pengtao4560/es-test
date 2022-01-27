package javase.oop.features;
import java.util.LinkedHashMap;
class LruCache{
	//has a (有一个)
	private LinkedHashMap<String,Object> cache;
	@SuppressWarnings("serial")
	public LruCache(final int maxSize) {
		this.cache=new LinkedHashMap<String, Object>(
				maxSize,0.75f,true){
			@Override
			protected boolean removeEldestEntry
					(java.util.Map.Entry<String,Object> eldest) {
				return size()>maxSize;
			};
		};
	}
	public void put(String key,Object value) {
		cache.put(key,value);
	}
	public Object get(String key) {
		return cache.get(key);
	}
	@Override
	public String toString() {
		return cache.toString();
	}
}
public class TestCompose03 {
	public static void main(String[] args) {
		LruCache c=new LruCache(2);
		c.put("A",100);
		c.put("B",200);
		c.get("A");
		c.put("C",300);
		c.get("A");
		c.put("D",400);
		System.out.println(c);
	}
}











