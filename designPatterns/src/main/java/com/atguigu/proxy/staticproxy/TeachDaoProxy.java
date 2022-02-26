package com.atguigu.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理模式-静态代理-
 *
 * @author pengtao
 * @createdate 2022/02/20 0020
 */
@Slf4j
public class TeachDaoProxy implements ITeacherDao {

    /*目标对象，通过接口来聚合*/
    private ITeacherDao teacherDaoTarget;

    public TeachDaoProxy(ITeacherDao teacherDaoTarget) {
        this.teacherDaoTarget = teacherDaoTarget;
    }

    @Override
    public void teach() {
        log.info("代理开始 完成某些操作(增强额外功能)。。。");

        // 增强额外功能

        teacherDaoTarget.teach();
        // 增强额外功能
        log.info("提交 增强额外功能结束。。。。");
    }
}
