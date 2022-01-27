package javase.serializable;
import java.io.Serializable;
/**支付方式*/
class Pay implements Serializable{
	private static final long serialVersionUID = -3273601913440748814L;
	private Integer id;
	private String payType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	@Override
	public String toString() {
		return "Pay [id=" + id + ", payType=" + payType + "]";
	}
}
/**使用hessian完成相关对象的序列化和反序列化*/
public class TestSerializable04 {
	public static void main(String[] args) throws Exception{
		Pay pay=new Pay();
		pay.setId(100);
		pay.setPayType("微信");
		HessianUtil.serialization(pay,"test.xml");
		pay=HessianUtil.deserialization("test.xml");
		System.out.println(pay);
	}
}
