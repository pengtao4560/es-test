package com.atguigu.facdate;

import lombok.extern.slf4j.Slf4j;

/**
 * 影院-外观模式
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Slf4j
public class HomeTheaterFacade {

    /* 定义各个子系统的对象 */
    private  TheaterLight theaterLight;
    private Popcorn popcorn;
    private Stereo stereo;
    private Projector projector;
    private Screen screen;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
    }

    public void ready() {
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
        log.info("--准备工作完成--");
    }

    public void play() {
        dvdPlayer.play();
        log.info("--播放中--");
    }

    public void pause() {
        dvdPlayer.pause();
        log.info("--已暂停--");
    }

    public void end() {
        popcorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        dvdPlayer.off();
        log.info("--结束--");
    }

}
