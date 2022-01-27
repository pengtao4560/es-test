package javase.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class HessianUtil {
	public static void serialization(Serializable obj,String file)throws Exception {
		Hessian2Output out =
		new Hessian2Output(new FileOutputStream(file));
		out.writeObject(obj);
		out.close();
	}
	@SuppressWarnings("unchecked")
	public static <T extends Serializable>T deserialization(String file)throws Exception{
		Hessian2Input in = new Hessian2Input(new FileInputStream(file));
		T obj = (T)in.readObject(null);
		in.close();
		return obj;
	}
}
