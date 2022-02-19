package com.atguigu.compite;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 组合模式- Componet
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Data
@AllArgsConstructor
public abstract class OrganizationComponent {

    /** 名字 */
    private String name;

    /** 说明 */
    private String description;

    protected void add(OrganizationComponent organizationComponent) {
        // 默认实现： 不能写成抽象：因为有叶子结点 不需要实现 添加方法
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        // 默认实现： 不能写成抽象：因为有叶子结点 不需要实现 添加方法
        throw new UnsupportedOperationException();
    }

    /** 信息打印 */
    protected abstract void print();
}
