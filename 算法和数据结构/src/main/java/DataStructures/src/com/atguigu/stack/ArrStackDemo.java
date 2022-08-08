package DataStructures.src.com.atguigu.stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
class ArrStack {
    int maxSize;
    int[] stackArr;
    int top = -1;

    public ArrStack(int maxSize) {
        this.maxSize = maxSize;
        stackArr = new int[maxSize];

    }

    /**
     o   4
     o   3
     o   2
     o   1
     o   0
        -1
     * @param num
     */
    public void push(int num) {
        if (top == stackArr.length - 1) {
            System.out.println("full cant push");
        } else {
            top++;
            stackArr[top] = num;
        }
    }

    public Integer pop() {
        if (top == -1) {
            System.out.println("stack is empyty");
            return null;
        }
        int result = stackArr[top];
        stackArr[top] = 0;
        top--;
        return result;
    }

    public void list() {
        System.out.println(Arrays.toString(stackArr));
    }
}
public class ArrStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrStack stack = new ArrStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: show");
            System.out.println("exit: exit");
            System.out.println("push: push");
            System.out.println("pop: pop");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("set a number");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("pop %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("exit~~~");
    }
}
