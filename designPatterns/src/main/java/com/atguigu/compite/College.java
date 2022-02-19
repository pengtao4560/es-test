package com.atguigu.compite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 学院
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Slf4j
public class College extends OrganizationComponent {

    /*List 中存放的是 Department */
    private List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String description) {
        super(name, description);
    }

    /** 输出 学院 包含的院系 Department */
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
