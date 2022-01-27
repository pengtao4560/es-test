package javase.generic;

import java.util.List;
import java.util.Map;

interface MybatisSession{
	  /**泛型方法:方法返回值左侧有<泛型>*/
	  <T>T selectList(String sql);
	  <T>T getMapper(Class<T> cls);
}
class DefaultMyBatisSession implements MybatisSession{
	  @Override
	  public <T>T selectList(String sql) {
		 return null;
	  }
	  @Override
	  public <T> T getMapper(Class<T> cls) {
		return null;
	  }
}

public class TestGeneric03 {
      public static void main(String[] args) {
    	  DefaultMyBatisSession session=
    	  new DefaultMyBatisSession();
    	  List<Map<String,Object>> list=
    	  session.selectList("select * from logs");
    	  Object obj=session.getMapper(Object.class);
      }
}
