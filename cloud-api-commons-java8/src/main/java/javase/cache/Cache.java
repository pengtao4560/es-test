package javase.cache;
public interface Cache<K,V> {
     /***
            * 向容器中放数据
      * @param key
      * @param value
      */
	 void putObject(K key,V value);
	 /**
	    * 从容器区数据
	  * @param key
	  * @return
	  */
	 V getObject(K key);

}
//class FIFO<V> implements  Cache<String,V>{
//
//	@Override
//	public void putObject(String key, V value) {
//
//	}
//
//	@Override
//	public V getObject(String key) {
//		return null;
//	}
//}





