package java8.exerStream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java8.stream.Employee;
import java8.stream.StreamAPITest;
import org.junit.Test;

public class TestStreamAPI {

	/*
	  	1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
		，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
	@Test
	public void test1(){
		Integer[] nums = new Integer[]{1,2,3,4,5};

		Arrays.stream(nums)
			  .map((x) -> x * x)
			  .forEach(System.out::println);
	}

	/*
	 2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 */
	List<Employee> emps = StreamAPITest.getEmployeeList();

	@Test
	public void test2(){
		Optional<Integer> count = emps.stream()
			.map((e) -> 1)
			.reduce(Integer::sum);

		System.out.println(count.get());
	}
}
