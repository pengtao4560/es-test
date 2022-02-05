package java8.lambda;

/**
 * @description: 字符串处理函数式接口
 * @author: peng tao
 * @create: 2022-02-02 10:01
 */
@FunctionalInterface
public interface MyStringUtilInterFace<T> {
    String getValue(T t);
}
