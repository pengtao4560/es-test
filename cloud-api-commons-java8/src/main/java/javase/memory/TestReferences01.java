package javase.memory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class Message{
	private int id;
	private String content;
	public Message(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}
	//对象在被回收之前会调用此方法
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize()");
	}
}
//-Xmx5m -Xms5m -XX:+PrintGCDetails
public class TestReferences01 {
	public static void main(String[] args) {
	  //1.msg变量为强引用(直接引用)
	  //Message msg=new Message(1,"CGB1904 高薪就业");
	  //msg=null;
	  //2 软引用:SoftReference (内存不足时会回收引用的对象)
	  //SoftReference<Message> sf=
	  //new SoftReference<Message>(new Message(1,"CGB1904 高薪就业"));
	  //System.out.println(sf.get());
	  //3 弱引用(只要有GC就可能会被回收)
	  WeakReference<Message> wr=
	  new WeakReference<Message>(new Message(1,"CGB1904 高薪就业"));
	  System.out.println(wr.get());
	  //手动启动GC
	  //System.gc();
	  //演示内存不足
	  List<byte[]> list=new ArrayList<byte[]>();
	  list.add(new byte[1024*1024]);
	  list.add(new byte[1024*1024]);
	  list.add(new byte[1024*1024]);
	  list.add(new byte[1024*1024]);
	  list.add(new byte[1024*1024]);
	}
}








