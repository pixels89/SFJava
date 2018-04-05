package ttl.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ttl.domain.Student;
import ttl.domain.Student.Status;
import ttl.service.StudentService;

public class RegAppPredicate {

	public static void main(String[] args) {
		RegAppPredicate ra = new RegAppPredicate();
		ra.go();
	}

	public void go() {
		StudentService service = new StudentService();

		List<Student> students = service.getAllStudents();
		
		NameChecker nc = new NameChecker("M");
		List<Student> result = findBy(students, nc);
		
		result.forEach(System.out::println);
		
		StatusChecker sc = new StatusChecker(Status.FULL_TIME);
		//result = findStudentsWithStatus(students, Status.FULL_TIME);

		result = findBy(students, new Predicate<Student>() {
			public boolean test(Student s) {
				return s.getStatus() == Status.FULL_TIME;
			}
		});

		result = findBy(students, s -> s.getStatus() == Status.FULL_TIME);

		result.forEach(System.out::println);

	}
	
	@FunctionalInterface
	public interface Checker<T> {
		public boolean  check(T s);
	}
	
	public class StatusChecker implements Checker<Student>
	{
		private Status prefix;
		public StatusChecker(Status prefix) {
			this.prefix = prefix;
		}

		public boolean check(Student s) {
			return s.getStatus() == prefix;
		}
	}

	public class NameChecker implements Predicate<Student>
	{
		private String prefix;
		public NameChecker(String prefix) {
			this.prefix = prefix;
		}

		public boolean test(Student s) {
			return s.getName().startsWith(prefix);
		}
	}
	public <T> List<T> findBy(List<T> input, Predicate<T> checker) {
		List<T> result = new ArrayList<>();
		for(T s : input) {
			if(checker.test(s)) {
				result.add(s);
			}
		}
		return result;
	}

	/*
	public List<Student> findStudentsWithStatus(List<Student> input, Status stat) {
		List<Student> result = new ArrayList<>();
		for(Student s : input) {
			if(s.getStatus() == stat) {
				result.add(s);
			}
		}
		return result;
	}
	*/

}
