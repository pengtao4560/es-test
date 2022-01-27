package javase.serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

class SysLog implements Serializable{
	private static final long serialVersionUID = -5018282550023483673L;
	//transient属性修饰的属性不需要序列化
	private transient int id;
	private String createdUser;
	//当在序列化时,执行了流对象的writeObject方法,
	//此时会调用如下方法(固定写法)
	private void writeObject(ObjectOutputStream oos)
	throws Exception{
		//进行加密
		Encoder encoder=Base64.getEncoder();
		createdUser=
		encoder.encodeToString(createdUser.getBytes());
	    //执行默认序列化
		oos.defaultWriteObject();
	}
	//当反序列化时,执行流对象的readObject方法.
	//此时会执行如下的readObject方法(固定写法,规范)
	private void readObject(ObjectInputStream ois)
			throws Exception{
		//先执行默认序列化
		ois.defaultReadObject();
		//对内容进行解密
		Decoder decoder=Base64.getDecoder();
		byte[] bytes=decoder.decode(createdUser);
	    createdUser=new String(bytes);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	@Override
	public String toString() {
		return "SysLog [id=" + id + ", createdUser=" + createdUser + "]";
	}
}
/**
  *  演示序列化的加密和解密
 * @author tarena
 */
public class TestSerializable03 {
	static void doEncoderDecoder() {
		String pwd="123456";
		//对字符串进行加密
		Encoder encoder=Base64.getEncoder();
		String newPwd=
		encoder.encodeToString(pwd.getBytes());
		System.out.println(newPwd);
		//对字符串进行解密
		Decoder decoder=Base64.getDecoder();
		byte[] bytes=decoder.decode(newPwd);
		pwd=new String(bytes);
		System.out.println(pwd);
	}
	public static void main(String[] args)
	throws Exception{
		//doEncoderDecoder();//加密解密演示
		SysLog log=new SysLog();
		log.setId(100);
		log.setCreatedUser("gaojian");
		//对象序列化
		ObjectOutputStream oos=
		new ObjectOutputStream(new FileOutputStream("f2"));
		oos.writeObject(log);
		oos.close();
		//对象反序列化
		ObjectInputStream ois=
		new ObjectInputStream(new FileInputStream("f2"));
	    Object obj=ois.readObject();
	    ois.close();
	    System.out.println(obj);
	}
}












