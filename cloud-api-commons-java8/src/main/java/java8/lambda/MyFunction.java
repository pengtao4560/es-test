package java8.lambda;/**
 * @author pt
 * @date 2022/2/2 - 10:20
 */

/**
 * @description: 尚硅谷java8第四节课练习 3-1
 * @author: peng tao
 * @create: 2022-02-02 10:20
 */
@FunctionalInterface
public interface MyFunction<T, R> {
    T getValue(T t, R r);
}
