package java8.lambda;
/** 使用设计模式优化 对实体类的过滤方法处理 */
public interface MyPredicate<T> {

	public boolean test(T t);

}
