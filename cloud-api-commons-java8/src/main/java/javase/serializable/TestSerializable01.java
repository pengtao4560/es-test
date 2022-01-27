package javase.serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
class Message implements Serializable{
	private static final long serialVersionUID = 7770654353954140625L;
	private transient int id;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}

}
public class TestSerializable01 {
    public static void main(String[] args)
    throws Exception{
		Message msg=new Message();
		msg.setId(100);
		msg.setContent("hello cgb1904");
		//将如上对象序列化到文件
		//1.构建流对象
		ObjectOutputStream out=
		new ObjectOutputStream(
		new FileOutputStream("f1.dat"));
		//2.将对象序列化
		out.writeObject(msg);
		//3.释放资源
		out.close();
		System.out.println("序列化ok");
		//将文件中的内容反序列化
		//1.构建流对象
		ObjectInputStream in=
		new ObjectInputStream(
		new FileInputStream("f1.dat"));
		Object obj=in.readObject();
		in.close();
		System.out.println(msg==obj);//false
		System.out.println(msg.equals(obj));//false
		System.out.println(obj);
	}
}












