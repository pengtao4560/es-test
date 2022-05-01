package java8.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;


/*
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStreamAPI2 {

	List<Employee> empList = StreamAPITest.getEmployeeList();

	//3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
	@Test
	public void test1(){
			boolean bl = empList.stream()
				.allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

			System.out.println(bl);

			boolean bl1 = empList.stream()
				.anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

			System.out.println(bl1);

			boolean bl2 = empList.stream()
				.noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

			System.out.println(bl2);
	}

	@Test
	public void test2(){
		Optional<Employee> op = empList.stream()
			.sorted((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()))
			.findFirst();

		System.out.println(op.get());

		System.out.println("--------------------------------");

		Optional<Employee> op2 = empList.parallelStream()
			.filter((e) -> e.getStatus().equals(Employee.Status.FREE))
			.findAny();

		System.out.println(op2.get());
	}

	@Test
	public void test3(){
		long count = empList.stream()
						 .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
						 .count();

		System.out.println(count);

		Optional<Integer> op = empList.stream()
			.map(Employee::getAge)
			.max(Integer::compare);

		System.out.println(op.get());

		Optional<Employee> op2 = empList.stream()
			.min((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));

		System.out.println(op2.get());
	}

	//注意：流进行了终止操作后，不能再次使用
	@Test
	public void test4(){
		Stream<Employee> stream = empList.stream()
		 .filter((e) -> e.getStatus().equals(Employee.Status.FREE));

		long count = stream.count();

		stream.map(Employee::getAge)
			.max(Integer::compare);
	}
}
