package DataStructures.src.com.atguigu.linkedlist;


import java.util.Scanner;

/**
 * 丢手绢问题
 *
 * 环形链表
 */
class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}

class CircleList {

    private Node first = null;

    public void addNode(int num) {

        if (num < 1) {
            System.out.printf(" limit 2 : ", num);
            return;
        }
        Node cur = null; // 辅助指针，帮助构建环形链表
        for (int i = 1; i <= num; i++) {
            Node node = new Node(i);
            if (i == 1) {
                first = node;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(node);
                node.setNext(first);
                cur = node;
            }
            //  cur
            //   \
            // first -  node
            //      \/

        }
    }

    public void show() {
        if (first == null) {
            System.out.println("no element");
            return;
        }
        Node cur = first;
        while (true) {
            System.out.println("cur no: " + cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /*      cur
             1  - 2 - 3 -4 -5
               \___________/

    * */

    // 根据用户的输入，计算出 丢手绢一圈小孩中，小孩子出圈的顺序

    /**
     *
     * @param startNo  从第几个小孩开始数数
     * @param countNum （报数）数几下
     * @param nums 最初有多少小孩围成一个圈子
     */
    public void countNode(int startNo, int countNum, int nums) {
        Node helper = first;
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }

        while (true) {
            if(helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 1; i <= startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (helper == first) {
                System.out.println("leave: " + first.getNo());
                break;
            }

            for (int i = 1; i <= countNum -1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时 first 指向的小孩就是要出圈的小孩
            System.out.printf("out Node is %d \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

public class CircleListDemo {
    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please set num, startNo, countNum...");
        int nums = scanner.nextInt();

        System.out.println("set num: " + nums);
        circleList.addNode(nums);
        circleList.show();

        int startNo = scanner.nextInt();
        System.out.println("set startNo: " + startNo);

        int countNum = scanner.nextInt();
        System.out.println("set countNum: " + countNum);

        circleList.countNode(startNo, countNum, nums);

        /*    num = 5 startNo = 1  countNum = 2            2-4-1-5-3
        *     1 - 2 -3
        *       \   /
        *        5-4
        * */
    }
}
