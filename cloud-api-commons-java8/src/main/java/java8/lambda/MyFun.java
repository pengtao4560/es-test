package java8.lambda;/**
 * @author pt
 * @date 2022/2/1 - 20:21
 */

/**
 * @description:
 * @author: peng tao
 * @create: 2022-02-01 20:21
 */
@FunctionalInterface
public interface MyFun<T> {
    void getValue(T t);
}
