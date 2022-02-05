package java8.lambda;

import java8.stream.Employee;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return Integer.parseInt(t.getSalary().toString()) >= 11650;
	}

}
