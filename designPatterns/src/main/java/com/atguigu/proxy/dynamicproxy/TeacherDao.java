package com.atguigu.proxy.dynamicproxy;

/**
 * 动态代理目标对象-  教师类
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
public class TeacherDao implements IteacherDao {

    @Override
    public void teach() {
        System.out.println(" 老师授课中... ");
    }
}
