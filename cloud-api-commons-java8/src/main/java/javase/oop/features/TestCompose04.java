package javase.oop.features;
class Male{
	private String name;
	private String birthday;
	public Male(String name) {
		this.name=name;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
class Female{
	private String name;
	public Female(String name) {
		this.name=name;
	}
}
class Family{
	private Male male; //组合
	private Female female;//组合
	public Family(Male male,Female female) {
		this.male=male;
		this.female=female;
	}
}
class Team{
	Family[] familys;//组合
}
public class TestCompose04 {
	public static void main(String[] args) {
		Male male=new Male("yangshu");
		male.setBirthday("2010-12-11");
		Female famale=new Female("liushu");
		//构造注入
		Family f=new Family(male, famale);
	}
}
