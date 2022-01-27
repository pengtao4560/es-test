package javase.oop.features;
interface HelloService{
	void sayHello();
}
class DefaultHelloService implements HelloService{
	@Override
	public void sayHello() {
		System.out.println("hello cgb1904");
	}
	public void sayWelcome() {
		System.out.println("welcome cgb1904");
	}
}
public class TestOopPolymorphic01 {
    public static void main(String[] args) {
		HelloService hService=
		new DefaultHelloService();
		//编译时看等号左边类型
//		hService.sayWelcome();
		((DefaultHelloService)hService).sayWelcome();
		hService.sayHello();
	}
}



