package ttl.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ttl.domain.Student;
import ttl.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		RegApp ra = new RegApp();
		ra.go();
	}

	public void go() {
		StudentService service = new StudentService();

		List<Student> students = service.getAllStudents();
		
		for(Student s : students) {
			
		}
		
		Map<String, Student> map = students.stream()
				.parallel()
			//.filter((s) -> s.getName().startsWith("M"))
			.collect(Collectors.toMap(s -> s.getName(), s -> s, (s1, s2) -> {
				System.out.println("in binop" +s1);	
				return s1;
			}));
		
		List<Student> list = students.stream().parallel()
			//.filter((s) -> s.getName().startsWith("M"))
			.collect(() -> new ArrayList<Student>(), 
					(l, obj) -> {
						l.add(obj);
					}, (list1, list2) -> {
						list1.addAll(list2);
					});

		List<Student> list2 = students.stream().parallel()
			//.filter((s) -> s.getName().startsWith("M"))
			.collect(ArrayList::new,
					ArrayList::add,
					ArrayList::addAll);
		
		list.forEach(System.out::println);
	}
	
	
}
