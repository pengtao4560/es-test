package com.atguigu.proxy.staticproxy;

/**
 * 代理模式-静态代理-客户端调用
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
public class StaticProxyClient {

    public static void main(String[] args) {

        // 创建目标对象
        TeacherDao teacherDao = new TeacherDao();

        // 创建代理对象，同时将被代理对象传递给代理对象
        TeachDaoProxy teachDaoProxy = new TeachDaoProxy(teacherDao);

        // 通过代理对象，调用被代理对象的方法
        // 即：执行的是代理对象的方法，代理对象再去调用被代理对象的方法
        teachDaoProxy.teach();

        /*
        14:26:20.114 [main] INFO com.atguigu.proxy.staticproxy.TeachDaoProxy - 代理开始 完成某些操作(增强额外功能)。。。
        老师授课中...
        14:26:20.117 [main] INFO com.atguigu.proxy.staticproxy.TeachDaoProxy - 提交 增强额外功能结束。。。。
        */
    }
}
