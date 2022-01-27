package javase.box;

public class TestInteger01 {

	//笔试题
	static public void main(String[] args) {
		Integer t1 = 100;//Integer.valueOf(100)
		Integer t2 = 100;
		Integer t3 = 200;
		Integer t4 = 200;
		System.out.println(t1 == t2);//true
		System.out.println(t3 == t4);//false
	}
}
