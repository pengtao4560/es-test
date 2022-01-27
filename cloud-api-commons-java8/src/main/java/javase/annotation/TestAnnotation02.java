package javase.annotation;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ComponentScan{
	String value();
}

@ComponentScan("com.cy.java.cache")
class AppConfig{}
public class TestAnnotation02 {
	public static void main(String[] args) {
	   //1.获取类的字节码对象
		Class<?> cls=AppConfig.class;
	   //2.获取类上的注解@ComponentScan
		ComponentScan cs=
		cls.getAnnotation(ComponentScan.class);
	   //3.获取注解中value属性的值
		String pkg=cs.value();
	   //4.将包结构中的"."替换为"/"
		String pkgPath=pkg.replace(".", "/");
	   //5.获取目录所在的绝对路径
		URL url=ClassLoader.getSystemResource(pkgPath);
		System.out.println("--- "+url);//--- file:/D:/workspace_IDEA_Learn/CGB-JAVASE-V1.01/target/classes/com/cy/java/cache
		File file=new File(url.getPath());
	   //6.获取路径下所有的文件
		File[] fs=file.listFiles();
		for(File f:fs) {
			System.out.println(f.getName());
		}
	}

}
