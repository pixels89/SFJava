package ttl.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ttl.domain.Student;
import ttl.service.StudentService;

public class RegAppMap {

	public static void main(String[] args) {
		RegAppMap ra = new RegAppMap();
		ra.go();
	}

	public void go() {
		StudentService service = new StudentService();

		List<Student> students = service.getAllStudents();
		
		List<String> names = getFields(students, new Function<Student, String>() {
			public String apply(Student s) {
				return s.getName();
			}
		});

		//names = getFields(students, s -> s.getName());
		names = getFields(students, Student::getName);
		
		names.forEach(System.out::println);

		List<Integer> ids = getFields(students, s -> s.getId());
		List<Integer> ids1 = getFields(students, Student::getId);
		
		ids.forEach(System.out::println);
	}
	
	
	public interface Extractor<T, R> {
		public R extract(T s );
	}
	
	
	public <T, R> List<R> getFields(List<T> input, Function<T, R> e) {
		List<R> result = new ArrayList<>();
		for(T s : input) {
			result.add(e.apply(s));
		}
		return result;
	}
}
