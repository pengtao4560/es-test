package com.atguigu.flyweight;

import java.util.HashMap;

/**
 * 享元模式- 网站工厂类-根据需求返回一个具体的网站
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class WebSiteFactory {

    // 池 集合
    private HashMap<String, ConcreteWebSite> pool = new HashMap<>();

    // 根据网站的类型，返回一个网站，如果没有就创建一个网站，并放入到池中
    public WebSite getWebSiteCategory(String type) {

        if (!pool.containsKey(type)) {
             // 创建一个网站，放到池中
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    /**
     * 获取网站分类的总数（池中有多少个网站类型）
     * */
    public int getWebSiteCount() {
        return pool.size();
    }

}
