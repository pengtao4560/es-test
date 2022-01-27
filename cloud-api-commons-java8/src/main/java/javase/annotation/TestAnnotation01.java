package javase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
/**自定义注解
 * @Retention 描述注解何时有效
 * @Target 描述注解可以修饰哪些对象
 * */
@Retention(RetentionPolicy.RUNTIME)//RUNTIME表示运行时有效
@Target(ElementType.TYPE)//type表示注解只能描述类
@interface Entity{//元数据(描述数据)
	//属性定义
	String value() default "";
}//所有注解默认继承Annotation

//定义ID注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@interface ID{
	String value() default "";
}
@Entity("goodsEntity")//<bean id="goods" class="com.cy.java.annotation.Goods" />
class Goods{
	@ID("gid")
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}
}
public class TestAnnotation01{
   public static void main(String[] args)throws Exception {
	 //1.获得Goods类的字节码对象
	  Class<Goods> c=Goods.class;
	  //2.获取Goods类上的Entity注解
	  Entity e=c.getAnnotation(Entity.class);
	  //jdk1.8
	  //e=c.getDeclaredAnnotation(Entity.class);
	  if(e!=null) {
	   //3.获取注解中的value属性的值
		String value=e.value();
		System.out.println(value);
	  }
	  //4.获取类中的ID属性
	  //c.getField(name)只能获取public修饰的属性
	  Field f=c.getDeclaredField("id");
	  //5.获取属性上的ID注解
	  ID id=f.getAnnotation(ID.class);
	  //6.获取ID注解value属性的值.
	  String idValue=id.value();
	  System.out.println(idValue);
   }
}
