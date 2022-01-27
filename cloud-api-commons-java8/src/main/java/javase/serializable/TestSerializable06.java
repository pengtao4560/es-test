package javase.serializable;
public class TestSerializable06 {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				doMethod01();
			}
		}.start();
		doMethod01();
	}
	private static void doMethod01() {
		Pay pay=new Pay();
		pay.setId(100);
		pay.setPayType("支付宝");
		byte[] array=KryoUtil.serialization(pay);
		pay=KryoUtil.deserialization(array, Pay.class);
	    System.out.println(pay);
	}
}




