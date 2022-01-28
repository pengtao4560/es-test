package javase.enums;


import javase.cache.Cache;

public class CacheFactory<K,V> {//MyBatis Configuration,new Exector();
	  public Cache<K,V> newCache(CacheType cacheType) {
		  switch (cacheType) {
		     case LRU: break;
		     case FIFO: break;
		     default:break;
		  }
		  return null;
	  }
}
