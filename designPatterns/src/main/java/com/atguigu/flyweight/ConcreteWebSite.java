package com.atguigu.flyweight;

/**
 * 享元模式 - 具体的网站
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class ConcreteWebSite extends WebSite {
    // 网站发布的形式、类型
    private String type;

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + " 在使用中, 使用者是：" + user.getName());
    }

    public ConcreteWebSite(String type) {
        this.type = type;
    }
}
