package com.atguigu.facdate;

/**
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class FacdateDemoClient {

    public static void main(String[] args) {
        // 这里直接调用。。。 很麻烦

        // 而使用外观模式，
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();

        homeTheaterFacade.pause();
        homeTheaterFacade.end();
    }
}
