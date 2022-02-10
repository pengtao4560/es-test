package javase.effectivejava.c28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 第一次优化
 * @author: peng tao
 * @create: 2022-02-10 10:37
 */
// Chooser - a class badly in need of generics!
public class ChooserNew<T> {

    private final T[] choiceArray;
    public ChooserNew(Collection<T> choices) {
        choiceArray = (T[]) choices.toArray();
    }
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}

