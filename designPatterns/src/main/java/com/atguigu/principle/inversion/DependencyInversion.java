package com.atguigu.principle.inversion;

/**
 * 依赖倒置原则
 * @author pt
 * @createdate 2022/02/12 0012
 */
public class DependencyInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

/**
 * 完成 Person接收消息的功能
 * 方式一完成
 * 分析：1.简单，比较容易想到
 *       2.可扩展差： 如果获取的对象是微信，短信等，则要新增类，同时Person类也要增加相应的接收方法
 *       3.解决思路： 引入一个抽象的接口 IReceiver，表示接收者， 这样Person类与接口发生依赖
 *       因为Email, Wechart, Message 等等属于接受的范围，他们各自实现IReceiver接口就ok。这样就符合依赖倒转原则
 *  优化方式
 * @see PersonPlus
 */
class Person {
    void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
class Email {
    String getInfo() {
        return "电子邮件信息：hello, world";
    }
}


