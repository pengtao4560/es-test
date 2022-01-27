package javase.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TestSerializable05 {
	public static void main(String[] args) {
		Kryo kryo = new Kryo();
		kryo.register(Pay.class);
		Pay pay=new Pay();
		pay.setId(100);
		pay.setPayType("支付宝");
		//对象序列化(写到内存数组)
		ByteArrayOutputStream bos=
		new ByteArrayOutputStream();
		Output output=new Output(bos);
		kryo.writeObject(output, pay);
		output.close();
		//对象反序列化
		byte[] buf=bos.toByteArray();
		ByteArrayInputStream inputStream=
		new ByteArrayInputStream(buf);
		Input input=new Input(inputStream);
		pay=kryo.readObject(input, Pay.class);
		input.close();
		System.out.println(pay);
	}
}




