package com.atguigu.compite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 大学 University 就是 Composite 可以管理 College
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Slf4j
public class University extends OrganizationComponent {

    private List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String description) {
        super(name, description);
    }

    /** 输出 University 包含的学院*/
    @Override
    protected void print() {
        log.info("------ {} -------", getName());
        // 遍历
        organizationComponents.forEach(OrganizationComponent::print);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }
}
