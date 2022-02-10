package javase.effectivejava.c28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 第二次改进后的 使用列表代替数组
 * @author: peng tao
 * @create: 2022-02-10 10:37
 */
// Chooser - a class badly in need of generics!
public class Chooser<T> {

    private final List<T> choiceList;
     // public Chooser(Collection<T> choices) {
    /* 第31章 优化成: */
    public Chooser(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);

    }
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}

