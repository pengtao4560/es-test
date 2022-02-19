package com.atguigu.compite;

import lombok.extern.slf4j.Slf4j;

/**
 * Department 院系
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Slf4j
public class Department extends OrganizationComponent {

    public Department(String name, String description) {
        super(name, description);
    }

    // add, remove 就不用写了，因为他是叶子结点

    /** 输出 院系的名称 */
    @Override
    protected void print() {
        log.info("------ {} -------", getName());
    }

}
