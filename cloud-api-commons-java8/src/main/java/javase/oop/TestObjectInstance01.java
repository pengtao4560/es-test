package javase.oop;
/**图形类型*/
class Shape{
	String name;
	public Shape() {}
	public Shape(String name) {
		this.name=name;
	}
}
/**空间中的一个点类型*/
class Point extends Shape{
	//类中属性存储于堆中
	private int x;
	private int y;
	private int z;
	//方法中的变量都存储于栈内存(线程私有)
	public Point(int x,int y){
		super("点");//调用父类带参数构造函数
		this.x=x;
		this.y=y;
	}
	public Point(int x,int y,int z){
		this(x,y);//调用本类带参构造函数
		this.z=z;
	}
}
public class TestObjectInstance01 {
	public static void main(String[] args) {
		Point p1=new Point(10,20);
		Point p2=new Point(10,20);
	}
}
