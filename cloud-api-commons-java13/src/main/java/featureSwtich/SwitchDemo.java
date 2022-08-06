package featureSwtich;

import cn.hutool.core.date.Week;
import featureSwtich.pojo.Fruit;

/**
 * @description: java12新特性 switch新写法
 * @author: peng tao
 * @create: 2022-01-27 13:22
 */
public class SwitchDemo {
    public static void main(String[] args) {
        System.out.println(getNumberOfLetters(Fruit.PEAR));
        System.out.println(getNumberOfLetters(Fruit.APPLE));
        System.out.println("------------");
        System.out.println(getNumberOfLetters(Week.SUNDAY));
        System.out.println(getNumberOfLetters(Week.TUESDAY));
        System.out.println(getNumberOfLetters(Week.WEDNESDAY));

        System.out.println("------------");
        // System.out.println(getNumberOfLetters(NULL));
    }
    /** 获取水果单词的字母数量*/
    private static int getNumberOfLetters(Fruit fruit){
     /*   int numberOfLetters = switch (fruit) {
            case PEAR -> 4;
            case APPLE,MANGO,GRAPE -> 5;
            case ORANGE,PAPAYA -> 6;
            default -> throw new IllegalStateException("No Such Fruit:" + fruit);
        };
        return numberOfLetters;*/
        return 1;
    }

    private static int getNumberOfLetters(Week day) {
/*        int numberOfLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
            default -> throw new IllegalStateException("What day is today?" + day);
        };
        return numberOfLetters;*/
        return 1;
    }
}
