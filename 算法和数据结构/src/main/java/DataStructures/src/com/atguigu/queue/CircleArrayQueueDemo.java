package DataStructures.src.com.atguigu.queue;

import java.util.Scanner;

/**
 ����˵һ�»��ζ������ж϶����Ƿ�������˼·�����������ѵ������
 ���{%}����
 {%}������һ����������㣬����һ����ϣ����
 ��Ϊȡģ��%���������԰�������ѹ����һ��������
 ���磬����֪��һ���е� 122 �������ڼ�����ô����á�121%7�������Ϊ����������ࡣ
 ��������������maxsize = 8 ��˵�� һ�����У������� % 8���Ľ��С��8
 ��Ʒ��[�ȴ�ϵ��_��ϸƷ]
 Ҳ����˵����������8ȡģ��%���൱�ڰ��������̶��ڴ���0��С��8�ķ�Χ��
 �����棿
 �����������ʽ��
 ��(rear+1)  %  maxsize == front��
 ��rearû��ָ���������һ��Ԫ��ʱ��Ҳ���ǵ���rear < maxsize - 1����ʱ��
 ��rear + 1���͵��ڡ�(rear+1) % maxSize��
 Ҳ���Ǵ�ʱȡģ��%�������൱�ڰ׸�������������

 ���ǵ�rearָ�����������һ��Ԫ��ʱ��
 rear + 1�ض���Խ�磬��ʱȡģ��%�������þͷ��ӳ����ˣ�
 ��Ϊȡģ��ʹ���������8�����õ��Ľ��ֻ��С��8
 ��Ȼ���ˣ��Ǿʹ�ͷ��ʼ�ɣ�
 ��ֽ���г�����г�һЩ����Ȼ��������Щ���г�������ʽ�ӣ����������ܿ��������ģ�
 ��������ȡģ��%��Ҳ����ȡ������һ���µ���ʶ������
 �㷨��֪ʶ�����ˣ�����
 */
public class CircleArrayQueueDemo {

	public static void main(String[] args) {

		//����һ��
		System.out.println("��������ģ�⻷�ζ��еİ���~~~");

		// ����һ�����ζ���
		CircleArray queue = new CircleArray(4); //˵������4, ����е���Ч���������3
		char key = ' '; // �����û�����
		Scanner scanner = new Scanner(System.in);//
		boolean loop = true;
		// ���һ���˵�
		while (loop) {
			System.out.println("s(show): ��ʾ����");
			System.out.println("e(exit): �˳�����");
			System.out.println("a(add): ������ݵ�����");
			System.out.println("g(get): �Ӷ���ȡ������");
			System.out.println("h(head): �鿴����ͷ������");
			key = scanner.next().charAt(0);// ����һ���ַ�
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("���һ����");
				int value = scanner.nextInt();
				int value2 = scanner.nextInt();
				System.out.println(value + "  " + value2);
				queue.addQueue(value);
				break;
			case 'g': // ȡ������
				try {
					int res = queue.getQueue();
					System.out.printf("ȡ����������%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h': // �鿴����ͷ������
				try {
					int res = queue.headQueue();
					System.out.printf("����ͷ��������%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'e': // �˳�
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("�����˳�~~");
	}

}

/***
 ˼·���£�
 1.front�����ĺ�����һ��������front��ָ����еĵ�һ��Ԫ�أ�Ҳ����˵arr[front]���Ƕ��еĵ�һ��Ԫ��
 front�ĳ�ʼֵ=0
 2.rear�����ĺ�����һ��������rearָ����е����һ��Ԫ�صĺ�һ��λ�á���Ϊϣ���ճ�һ���ռ���ΪԼ��
 rear�ĳ�ʼֵ=0
 3.��������ʱ��������(rear+1)%maxSize==front������
 4.�Զ���Ϊ�յ�������rear==front��
 5.������������������������Ч�����ݵĸ���(rear + maxSize - front) % maxSize;  // rear=1 front=0
 6.���ǾͿ�����ԭ���Ķ������޸ĵõ���һ�����ζ���
 */
class CircleArray {
	private int maxSize; // ��ʾ������������
	//front �����ĺ�����һ�������� front ��ָ����еĵ�һ��Ԫ��, Ҳ����˵ arr[front] ���Ƕ��еĵ�һ��Ԫ��
	//front �ĳ�ʼֵ = 0
	private int front;
	//rear �����ĺ�����һ��������rear ָ����е����һ��Ԫ�صĺ�һ��λ��. ��Ϊϣ���ճ�һ���ռ���ΪԼ��.
	//rear �ĳ�ʼֵ = 0
	private int rear; // ����β
	private int[] arr; // ���������ڴ������, ģ�����

	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}

	// �ж϶����Ƿ���
	public boolean isFull() {
		return (rear  + 1) % maxSize == front;
		//�ؼ�����һ������ģ��ѭ�����У����￴ line5- line 28 ��ע��
	}

	// �ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return rear == front;
	}

	// ������ݵ�����
	public void addQueue(int n) {
		// �ж϶����Ƿ���
		if (isFull()) {
			System.out.println("�����������ܼ�������~");
			return;
		}
		//ֱ�ӽ����ݼ���
		arr[rear] = n;
		//�� rear ����, ������뿼��ȡģ
		rear = (rear + 1) % maxSize;
	}

	// ��ȡ���е�����, ������
	public int getQueue() {
		// �ж϶����Ƿ��
		if (isEmpty()) {
			// ͨ���׳��쳣
			throw new RuntimeException("���пգ�����ȡ����");
		}
		// ������Ҫ������ front��ָ����еĵ�һ��Ԫ��
		// 1. �Ȱ� front ��Ӧ��ֵ������һ����ʱ����
		// 2. �� front ����, ����ȡģ
		// 3. ����ʱ����ı�������
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;

	}

	// ��ʾ���е���������
	public void showQueue() {
		// ����
		if (isEmpty()) {
			System.out.println("���пյģ�û������~~");
			return;
		}
		// ˼·����front��ʼ�������������ٸ�Ԫ��
		// ���Խ�
		for (int i = front; i < front + size() ; i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// �����ǰ������Ч���ݵĸ���
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3
		return (rear + maxSize - front) % maxSize;
	}

	// ��ʾ���е�ͷ���ݣ� ע�ⲻ��ȡ������
	public int headQueue() {
		// �ж�
		if (isEmpty()) {
			throw new RuntimeException("���пյģ�û������~~");
		}
		return arr[front];
	}
}
