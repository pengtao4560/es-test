package javase.oop.features;


import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 *  构建一个Cache对象:FifoCache
 * 1)存储结构:哈希表(例如可借助HashMap存储)
 * 2)算法:先进先出(FIFO):可借助LinkedList记录顺序
 */
class FifoCache{
	//has a
	private Map<String,Object> cache;
	//has a
	private LinkedList<String> orderKeys;
	/**记录最多有多少个有效元素*/
	private int maxSize;
	public FifoCache(int maxSize) {
		this.maxSize=maxSize;
	}
	public void setOrderKeys(LinkedList<String> orderKeys) {
		this.orderKeys = orderKeys;
	}
	public void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}
	public void put(String key,Object value) {
		//1.记录key(永远添加在最后一个元素的后面)
		orderKeys.addLast(key);//LinkedList
		//2.移除元素(假如满了)
		if(orderKeys.size()>maxSize) {
			//2.1移除LinkedList中移除第一个元素
			String eldestKey= orderKeys.removeFirst();
			//2.2移除cache中的元素
			cache.remove(eldestKey);
		}
		//3.放新元素
		cache.put(key, value);
	}
	@Override
	public String toString() {
		return cache.toString();
	}
}
public class TestCompose02 {
	public static void main(String[] args) {
		FifoCache cache=new FifoCache(2);//size
		cache.setOrderKeys(new LinkedList<String>());
		cache.setCache(new HashMap<String, Object>());
		cache.put("A",100);
		cache.put("B",200);
		cache.put("C",300);
		cache.put("D",300);
		cache.put("E",300);
		PrintStream ps=System.out;
		ps.println(cache);//B,C
	}
}
///*实现一个先进先出的缓存*/
//class Fifocache<K,V> implements Cache {
//	private int maxSize;
//	private Map<Object, Object> cache = new HashMap<Object, Object>();
//	private LinkedList<Object> list = new LinkedList<Object>();
//
//	public Fifocache(int size) {
//		this.maxSize = size;
//	}
//
//	@Override
//	public void putObject(Object key, Object value) {
//		if (cache.size()>=maxSize){
//			Object k = list.removeFirst();
//			cache.remove(k);
//		}
//		list.addLast(key);
//		cache.put(key, value);
//	}
//
//	@Override
//	public Object getObject(Object key) {
//		return cache.get(key);
//	}
//	public Object removeObject(Object key) throws Exception {
//		Object removedKey = cache.remove(key);
//		if (removedKey == null){
//			return "无此元素";
//		}
//		list.remove(removedKey);
//		return removedKey;
//	}
//
//	@Override
//	public String toString() {
//		return cache.toString();
//	}
//}
//public class TestCompose02{
//	public static void main(String[] args) throws Exception {
//		Fifocache<String, Integer> fifocache = new Fifocache<>(3);
//		fifocache.putObject("a", 3);
//		fifocache.putObject("b", 3);
//		fifocache.putObject("c", 3);
//		fifocache.putObject("d", 3);
//		Object a = fifocache.removeObject("b");
//		System.out.println(a);
//		fifocache.putObject("e", 3);
//		fifocache.putObject("f", 3);
//		System.out.println(fifocache.toString());
//	}
//}











