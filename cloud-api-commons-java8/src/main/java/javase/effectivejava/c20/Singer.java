package javase.effectivejava.c20;

import java.applet.AudioClip;

/**
 * effectivejava 20 【接口优于抽象类】 pdf-168
 * ITEM 20: PREFER INTERFACES TO ABSTRACT CLASSES
 * @author: peng tao
 * @create: 2022-01-28 13:26
 * @see java.util.Map.Entry
 */
/**歌手*/
interface Singer {
    /** 演唱 */
    AudioClip sing(Song s);
}
/**作曲家*/
interface Songwriter {
    /** 作曲 */
    Song compose(int chartPosition);
}
/** 在现实生活中，一些歌手也是作曲家。因为我们使用接口而不是抽象类来定义这些类型，所以单个类实现歌
 手和作曲家两个接口是完全允许的。事实上，我们可以定义一个继承歌手和作曲家的第三个接口，并添加适合于
 这个组合的新方法：
*/
interface SingerSongwriter extends Singer, Songwriter {
    /*添加适合于这个组合的新方法*/
    /** 弹奏 */
    AudioClip strum();
    /** 动作感知? */
    void actSensitive();
}
class Song {
}
/** @see */
