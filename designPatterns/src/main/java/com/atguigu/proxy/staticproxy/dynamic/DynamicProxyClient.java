package com.atguigu.proxy.staticproxy.dynamic;

import com.atguigu.proxy.dynamicproxy.TeacherDao;

/**
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
public class DynamicProxyClient {

    public static void main(String[] args) {

        TeacherDao teacherDaoTarget = new TeacherDao();
        // 此处需要把 返回值强制转换为 target的类型。
        // Object proxyInstance = new ProxyFactory(teacherDaoTarget).getProxyInstance();
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(teacherDaoTarget).getProxyInstance();

        System.out.println("proxyInstance: " + proxyInstance);
        proxyInstance.teach();
    }
}
