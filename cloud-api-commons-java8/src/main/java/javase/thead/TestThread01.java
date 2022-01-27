package javase.thead;
/**
 *  如何保证线程安全?
 * 导致线程不安全的原因是什么?
 *1)多个线程并发执行
 *2)多个线程有共享数据集
 *3)多个线程在共享数据集上的操作是非原子操作
 * @author tarena
 *
 */

public class TestThread01 {

	static String content;
	public static void main(String[] args)throws Exception {
		Thread t=new Thread() {
			@Override
			public void run() {
				synchronized (TestThread01.class) {
					content="helloworld";
					TestThread01.class.notifyAll();
				}
			}
		};
		t.start();//就绪
		//t.join();
		//while(content==null) { Thread.yield(); }
		synchronized (TestThread01.class) {
			while(content==null)
				TestThread01.class.wait();
			System.out.println(content.toUpperCase());
		}
	}
}
