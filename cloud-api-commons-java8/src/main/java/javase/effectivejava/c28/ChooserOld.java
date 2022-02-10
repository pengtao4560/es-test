package javase.effectivejava.c28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description:优化前
 * @author: peng tao
 * @create: 2022-02-10 10:37
 */
// Chooser - a class badly in need of generics!
public class ChooserOld {

    private final Object[] choiceArray;
    public ChooserOld(Collection choices) {
        choiceArray =  choices.toArray();
    }
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}

