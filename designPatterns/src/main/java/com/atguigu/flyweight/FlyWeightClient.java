package com.atguigu.flyweight;

/**
 * 享元模式 客户端调用
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class FlyWeightClient {

    public static void main(String[] args) {

        // 创建一个工厂类
        WebSiteFactory webSiteFactory = new WebSiteFactory();

        // 客户要以一个新闻形式发布的网站
        WebSite webSite = webSiteFactory.getWebSiteCategory("新闻");
        webSite.use(new User("李彦宏"));

        WebSite webSite2 = webSiteFactory.getWebSiteCategory("微博");
        webSite2.use(new User("张朝阳"));

        WebSite webSite3 = webSiteFactory.getWebSiteCategory("微博");
        webSite2.use(new User("马化腾"));

        WebSite webSite4 = webSiteFactory.getWebSiteCategory("微博");
        webSite2.use(new User("刘强东"));

        System.out.println("网站的分类共：" + webSiteFactory.getWebSiteCount());
    }
}
