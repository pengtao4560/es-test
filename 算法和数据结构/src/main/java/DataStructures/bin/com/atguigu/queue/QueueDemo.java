package DataStructures.bin.com.atguigu.queue;

/**
 *
 */
public class QueueDemo {
    // 用数组模拟队列
    // 队列需要的属性： 队列数组、队列长度（最大值）、
    // front指针指向队列头的前一个数据
    // real 指针指向队列尾部。
    // 入队列方法、出队列方法、队列是否空、满、队列第一个数据是什么
    public int maxSize;
    int front;
    int real;
    int[] arr; // 尚未初始化

    public QueueDemo(int Size){
        maxSize = Size;
        arr = new int[maxSize];
        front = -1;
        real = -1;
    }

    public boolean isFull() {
        return real == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == real;
    }

    public void put(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return;
        }
        real++;
        arr[real] = num;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        front++;
        return arr[front];
    }

    public void headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }

    }
    public void list() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\t", i, arr[i]);
        }
    }

    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo(3);
        queueDemo.put(1);
        queueDemo.put(2);
        queueDemo.put(3);
        queueDemo.put(4);
        queueDemo.list();
        System.out.println();
        System.out.println(queueDemo.get());
        System.out.println(queueDemo.get());
        System.out.println(queueDemo.get());
        System.out.println(queueDemo.get());
    }
}
