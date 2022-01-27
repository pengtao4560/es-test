package javase.serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  商品对象
 * @author tarena
 */
class Goods{
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + "]";
	}
}
//JSON是一种数据格式
//1)满足json格式的字符串,我们称之为JSON字符串
//2)满足JSON格式的对象,称之为JSON对象(javascript)
public class TestJacksonSerializable01 {
	public static void main(String[] args) throws Exception{
		Goods s1=new Goods();
		s1.setId(100);
		s1.setName("方便面");
		//将对象转换为json格式的字符串
		//{"id":100,"name":"方便面"}
		String json01=doJsonFromObject01(s1);
		System.out.println(json01);
		String json02=doJsonFromObject02(s1);
		System.out.println(json02);
		Goods s2=doObjectFromJson01(json02);
		System.out.println(s2);
	}
	static String doJsonFromObject01(Goods goods) {
		return "{\"id\":"+goods.getId()+",\"name\":\""+goods.getName()+"\"}";
	}
	//使用jackson库中的API将对象转换为JSON格式的字符串
	static String doJsonFromObject02(Goods goods)
	throws Exception{
		//1.构建json转换器对象
		ObjectMapper om=new ObjectMapper();
		//2.将对象转换为json格式字符串
		return om.writeValueAsString(goods);
	}
	static Goods doObjectFromJson01(String jsonStr)
			throws Exception{
		 //1.构建json转换器对象
		 ObjectMapper om=new ObjectMapper();
		 return om.readValue(jsonStr,Goods.class);
	}
	//fastjson,Gson
}











