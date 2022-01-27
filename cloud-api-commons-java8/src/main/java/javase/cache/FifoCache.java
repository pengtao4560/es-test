package javase.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
  *  存储结构:HashMap
  *  算法:FIFO
 * @param <K>
 * @param <V>
 */
public class FifoCache<K,V> implements Cache<K,V>{
    private Map<K,V> cache=new HashMap<K, V>();
	private LinkedList<K> orderKeys=new LinkedList<K>();
    private int maxCap;
    public FifoCache(int maxCap) {
    	this.maxCap=maxCap;
	}
	@Override
	public void putObject(K key, V value) {
		orderKeys.addLast(key);
		if(orderKeys.size()>maxCap) {
			K eldestKey=orderKeys.removeFirst();
			cache.remove(eldestKey);
		}
		cache.put(key, value);
	}
	@Override
	public V getObject(K key) {
		return cache.get(key);
	}
	@Override
	public String toString() {
	  return cache.toString();
	}
	public static void main(String[] args) {
		FifoCache<String,Object> cache=
		new FifoCache<String,Object>(3);
		cache.putObject("A", 100);
		cache.putObject("D", 400);
		cache.putObject("C", 300);
		cache.putObject("B", 200);
		System.out.println(cache);
	}
}
