package javase.thead;

import java.util.concurrent.CountDownLatch;

/**
 *  如何保证线程安全?
 * 导致线程不安全的原因是什么?
 *1)多个线程并发执行
 *2)多个线程有共享数据集
 *3)多个线程在共享数据集上的操作是非原子操作
 * @author tarena
 *
 */

public class TestThread02 {

	static String content;
	public static void main(String[] args)throws Exception {
		CountDownLatch dLatch=new CountDownLatch(1);
		Thread t=new Thread() {
			@Override
			public void run() {
					content="helloworld";
					dLatch.countDown();
			}
		};
		t.start();//就绪
		while(content==null)
		dLatch.await();
		System.out.println(content.toUpperCase());

	}
}
