package javase.enums;
/**定义枚举*/
enum Gender{//Gender.class
	MALE, //男士
	FEMALE,//女士
	NONE;//没有性别要求
	//如上三个值为对象实例(类加载时创建)
}
/**商品对象*/
class Goods{//pojo
	/**性别要求*/
	private Gender gender=Gender.NONE;
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
public class TestEnum01 {
    public static void main(String[] args) {
		Goods g=new Goods();
		String gender="MALE";
		//将字符串转换为枚举类型.
		g.setGender(Gender.valueOf(gender));
	}
}




