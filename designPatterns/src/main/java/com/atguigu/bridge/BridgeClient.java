package com.atguigu.bridge;

/**
 * 桥接模式 客户端调用
 * @author pengtao
 * @createdate 2022/02/18 0018
 */
public class BridgeClient {

    public static void main(String[] args) {

        // 获取折叠式手机（样式+品牌）
        Phone xiaomiFoldedPhone = new FoldedPhone(new XiaoMi());
        xiaomiFoldedPhone.open();
        xiaomiFoldedPhone.call();
        xiaomiFoldedPhone.close();

        System.out.println("----------");
        Phone foldedVivoPhone = new FoldedPhone(new Vivo());
        foldedVivoPhone.open();
        foldedVivoPhone.call();
        foldedVivoPhone.close();
    }
}
