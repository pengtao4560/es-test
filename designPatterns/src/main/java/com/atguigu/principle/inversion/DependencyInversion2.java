package com.atguigu.principle.inversion;

/**
 * @author pt
 * @createdate 2022/02/12 0012
 * @desc 依赖倒置原则
 */
public class DependencyInversion2 {
    public static void main(String[] args) {
        PersonPlus personPlus = new PersonPlus();
        EmailPlus emailPlusReceiver = new EmailPlus();
        personPlus.receive(emailPlusReceiver);
        personPlus.receive(new WeChart());

        System.out.println("----------");
        PersonPlusConstructor  personPlusConstructor = new PersonPlusConstructor(emailPlusReceiver);
        personPlusConstructor.receive();

        System.out.println("----------");
        PersonPlusSetter personPlusSetter = new PersonPlusSetter();
        personPlusSetter.setiReceiver(emailPlusReceiver);
        personPlusSetter.receive();
    }
}

/**
 * 完成 Person接收消息的功能
 * 最初方案：
 *
 * @see DependencyInversion
 * 解决思路： 引入一个抽象的接口 IReceiver，表示接收者， 这样Person类与接口发生依赖
 * 因为Email, Wechart, Message 等等属于接受的范围，他们各自实现 IReceiver 接口就ok。这样就符合依赖倒转原则
 */

class PersonPlus {
    void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}
/** 通过构造方法进行依赖传递 */
class PersonPlusConstructor {
    private IReceiver iReceiver;

    public PersonPlusConstructor(IReceiver iReceiver) {
        this.iReceiver = iReceiver;
    }
    void receive() {
        System.out.println(iReceiver.getInfo());
    }

}
/** 通过setter方法进行依赖传递 */
class PersonPlusSetter {
    private IReceiver iReceiver;

    public IReceiver getiReceiver() {
        return iReceiver;
    }

    public void setiReceiver(IReceiver iReceiver) {
        this.iReceiver = iReceiver;
    }
    void receive() {
        System.out.println(iReceiver.getInfo());
    }

}


interface IReceiver {
    String getInfo();
}

class EmailPlus implements IReceiver {

    @Override
    public String getInfo() {
        return "电子邮件信息：hello Email";
    }
}
/** 扩展类 微信消息*/
class WeChart implements IReceiver {

    @Override
    public String getInfo() {
        return "微信信息：hello WeChart";
    }
}


