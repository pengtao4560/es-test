package javase.oop.features;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * LinkedHashMap 特殊hashMap,可以通过链表
 * 1)记录元素添加顺序(默认)
 * 2)记录元素访问顺序(需要设置)
 */
class SynchronizedLruCache<K,V> extends LinkedHashMap<K, V>{
	private int maxSize;
	public SynchronizedLruCache(int maxSize) {
		super((int)Math.ceil(maxSize/0.75f)+1,//initialCapacity
			  0.75f,//loadFactor
			  true//accessOrder (true表示记录访问顺序)
			  );
		this.maxSize=maxSize;
	}
	@Override
	public synchronized V put(K key, V value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}
	@Override
	public synchronized V get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}
	//借助此map存储删除的元素
	private Map<K,V> removedMap=new LinkedHashMap<K, V>();
	//此方法在每次执行put操作时都会执行
	@Override
	protected synchronized boolean removeEldestEntry(
			Map.Entry<K, V> eldest) {
		boolean flag=size()>maxSize;
		if(flag) {//true
		 removedMap.put(eldest.getKey(),
				 eldest.getValue());
		}
		return flag;
	}
	public Map<K, V> getRemovedMap() {
		return removedMap;
	}
}
public class TestOopExtends02 {
	public static void main(String[] args) {
		SynchronizedLruCache<String,Integer> cache=
			new SynchronizedLruCache<String,Integer>(3);
		cache.put("A",100);
		cache.put("D",400);
		cache.put("B",200);
		cache.get("A");
		cache.put("C",300);
		System.out.println(cache);
		System.out.println(cache.getRemovedMap());

	}
}
