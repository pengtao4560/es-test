package javase.enums;

import java.util.EnumMap;

enum Sex{//Sex
	MALE("男士"),FEMALE("女士");
	private String name;
	/**枚举类中的构造方法必须是私有的*/
	private Sex(String name) {
		this.name=name;
	}
	public void doPrint() {
		System.out.println(name);
	}
}
/**会员类型*/
class Member{
	/**性别*/
	private Sex sex=Sex.MALE;
	public Sex getSex() {
		return sex;
	}
}
public class TestEnum02 {
    public static void main(String[] args) {
    	//调用枚举对象的方法
    	Sex.FEMALE.doPrint();
    	//获取会员对象的性别
    	Member m=new Member();
    	m.getSex().doPrint();
    	//java中所有的枚举类型都可以看成是Enum类型
    	boolean flag=Sex.FEMALE instanceof Enum;
    	//将字符串转换为枚举类型
    	//方式1
    	Sex sex=
    	Enum.valueOf(Sex.class, "MALE");
    	//方式2
    	sex=Sex.valueOf("MALE");

    	//枚举map对象的应用
    	EnumMap<Sex,String> map=
    	new EnumMap<Sex, String>(Sex.class);
    	map.put(Sex.MALE,"男");
    	map.put(Sex.FEMALE,"女");
    	System.out.println(map);
	}
}





