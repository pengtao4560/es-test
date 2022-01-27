package javase.effectivejava.dependencyInjection1_5;

import java.util.ArrayList;
import java.util.List;

/**
 * 1-5  依赖注入优于硬连接资源（hardwiring resources）
 * @description:    将资源或工厂传递给构造方法（或静态工厂或 builder 模式）。
 *                  这种称为依赖注入的实践将极大地增强类的灵活性、可重用性和可测试性。
 * @author: peng tao
 * @create: 2021-09-23 09:51
 */
// Inappropriate use of static utility - inflexible & untestable!
public class SpellChecker {
    private static final Lexicon dictionary = new Lexicon();

    private SpellChecker() {
    } // Noninstantiable

    public static boolean isValid(String word) {
        //// TODO: 2021/9/23
        return false;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }

    private static class Lexicon {
    }
}
