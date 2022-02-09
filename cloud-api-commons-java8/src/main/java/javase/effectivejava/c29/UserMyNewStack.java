package javase.effectivejava.c29;

/**
 * @description: 泛型类的使用 以相反的顺序打印其命令行参数，并将其转换为大写
 * @author: peng tao
 * @create: 2022-02-08 14:55
 */
public class UserMyNewStack {
    public static void main(String[] args) {
        MyNewStack<String> stack = new MyNewStack<>();
        args = new String[]{"a", "b", "C", "d", "E", "f"};
        for (String arg : args) {
            stack.push(arg);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
            /*控制台输出结果：
            F
            E
            D
            C
            B
            A
            * */
        }
    }
}
