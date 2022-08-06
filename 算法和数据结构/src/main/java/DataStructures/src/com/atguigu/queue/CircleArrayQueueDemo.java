package DataStructures.src.com.atguigu.queue;

import java.util.Scanner;

/**
 我来说一下环形队列中判断队列是否已满的思路，这个问题的难点就在于
 理解{%}运算
 {%}运算是一个神奇的运算，它是一个哈希运算
 因为取模【%】操作可以把任意数压缩到一定区间内
 比如，你想知道一年中第 122 天是星期几，那么你就用【121%7】，结果为二。诸如此类。
 就拿这个例子里的maxsize = 8 来说， 一定会有（任意数 % 8）的结果小于8
 你品，[热词系列_你细品]
 也就是说，任意数对8取模【%】相当于把任意数固定在大于0，小于8的范围内
 神不神奇？
 在下面这个等式中
 【(rear+1)  %  maxsize == front】
 当rear没有指向数组最后一个元素时，也就是当【rear < maxsize - 1】的时候
 【rear + 1】就等于【(rear+1) % maxSize】
 也就是此时取模【%】运算相当于白给，不信你算算

 但是当rear指向了数组最后一个元素时，
 rear + 1必定会越界，此时取模【%】的作用就发挥出来了，
 因为取模，使得任意大于8的数得到的结果只能小于8
 既然超了，那就从头开始吧！
 在纸上列出随便列出一些数，然后随便对这些数列出除法的式子（就是那种能看到余数的）
 相信你会对取模【%】也就是取余数有一个新的认识！！！
 算法的知识增加了！！！
 */
public class CircleArrayQueueDemo {

	public static void main(String[] args) {

		//测试一把
		System.out.println("测试数组模拟环形队列的案例~~~");

		// 创建一个环形队列
		CircleArray queue = new CircleArray(4); //说明设置4, 其队列的有效数据最大是3
		char key = ' '; // 接收用户输入
		Scanner scanner = new Scanner(System.in);//
		boolean loop = true;
		// 输出一个菜单
		while (loop) {
			System.out.println("s(show): 显示队列");
			System.out.println("e(exit): 退出程序");
			System.out.println("a(add): 添加数据到队列");
			System.out.println("g(get): 从队列取出数据");
			System.out.println("h(head): 查看队列头的数据");
			key = scanner.next().charAt(0);// 接收一个字符
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数");
				int value = scanner.nextInt();
				int value2 = scanner.nextInt();
				System.out.println(value + "  " + value2);
				queue.addQueue(value);
				break;
			case 'g': // 取出数据
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h': // 查看队列头的数据
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'e': // 退出
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出~~");
	}

}

/***
 思路如下：
 1.front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
 front的初始值=0
 2.rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置。因为希望空出一个空间做为约定
 rear的初始值=0
 3.当队列满时，条件是(rear+1)%maxSize==front【满】
 4.对队列为空的条件，rear==front空
 5.当我们这样分析，队列中有效的数据的个数(rear + maxSize - front) % maxSize;  // rear=1 front=0
 6.我们就可以在原来的队列上修改得到，一个环形队列
 */
class CircleArray {
	private int maxSize; // 表示数组的最大容量
	//front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
	//front 的初始值 = 0
	private int front;
	//rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
	//rear 的初始值 = 0
	private int rear; // 队列尾
	private int[] arr; // 该数据用于存放数据, 模拟队列

	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}

	// 判断队列是否满
	public boolean isFull() {
		return (rear  + 1) % maxSize == front;
		//关键是这一步，来模拟循环队列，这里看 line5- line 28 类注释
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}

	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满
		if (isFull()) {
			System.out.println("队列满，不能加入数据~");
			return;
		}
		//直接将数据加入
		arr[rear] = n;
		//将 rear 后移, 这里必须考虑取模
		rear = (rear + 1) % maxSize;
	}

	// 获取队列的数据, 出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) {
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		// 这里需要分析出 front是指向队列的第一个元素
		// 1. 先把 front 对应的值保留到一个临时变量
		// 2. 将 front 后移, 考虑取模
		// 3. 将临时保存的变量返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;

	}

	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) {
			System.out.println("队列空的，没有数据~~");
			return;
		}
		// 思路：从front开始遍历，遍历多少个元素
		// 动脑筋
		for (int i = front; i < front + size() ; i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// 求出当前队列有效数据的个数
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3
		return (rear + maxSize - front) % maxSize;
	}

	// 显示队列的头数据， 注意不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空的，没有数据~~");
		}
		return arr[front];
	}
}
