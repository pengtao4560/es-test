package scanner;

import java.util.Scanner;

/**
 *
 */
public class ScannerTest {
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);	//构造Scanner类的对象scan，接收从控制台输入的信息
        System.out.println("请输入你的姓名");
        String name = scan.nextLine();//接收一个字符串，可以加除Enter以外的所有符号，包括空格和Tab
        System.out.println("请输入你的ID");
        String ID ;
        while(scan.hasNextLine()) {// hasNextLine()方法判断当前是否有输入，当键盘有输入后执行循环
            if(scan.hasNextInt()) {// 判断输入的值是否为整数类型，当为整数类型时执行循环
                ID = scan.nextLine();
                System.out.println("你输入的姓名为："+name);
                System.out.println("你输入的ID为："+ID);
                break;
            } else {
                System.out.println("请输入数字哦！");
                ID = scan.nextLine();
                continue;
            }
        }

    }
}

