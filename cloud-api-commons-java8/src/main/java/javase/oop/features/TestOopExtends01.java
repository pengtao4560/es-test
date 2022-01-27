package javase.oop.features;
class DefaultSearchService{
	public Object search(String key) {
		//System.out.println("start:"+System.currentTimeMillis());
		System.out.println("search by "+key);
		//System.out.println("end:"+System.currentTimeMillis());
		return null;
	}
}
//基于OCP原则对已有功能进行扩展
//OCP:对扩展开放，对修改关闭
class LogSearchService extends DefaultSearchService{
	@Override
	public Object search(String key) {
		System.out.println("start:"+System.currentTimeMillis());
		Object result=super.search(key);
		System.out.println("end:"+System.currentTimeMillis());
	    return result;
	}
}
public class TestOopExtends01 {
	public static void main(String[] args) {
		LogSearchService service=new LogSearchService();
		service.search("cgb1904");
	}
}
