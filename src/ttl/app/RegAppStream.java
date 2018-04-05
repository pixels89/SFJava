package ttl.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ttl.domain.Student;
import ttl.service.StudentService;

public class RegAppStream {

	public static void main(String[] args) {
		RegAppStream ra = new RegAppStream();
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
		
		List<Student> l3 = students.stream().parallel()
			.filter((s) -> s.getName().startsWith("M"))
			.collect(Collectors.toList());

		List<Student> l8 = students.stream().parallel()
			.filter((s) -> s.getName().startsWith("M"))
			.collect(() -> new ArrayList<Student>(), 
					(studentList, student) -> studentList.add(student),
					(l1, l2) -> l1.addAll(l2));
		
		List<Student> list = students.stream().parallel()
			//.filter((s) -> s.getName().startsWith("M"))
			.collect(() -> new ArrayList<Student>(), 
					(l, obj) -> {
						l.add(obj);
					}, (list1, list2) -> {
						list1.addAll(list2);
					});
		
		List<Student> l5 = students.stream().parallel()
			//.filter((s) -> s.getName().startsWith("M"))
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		
		list.forEach(System.out::println);
		
		int data [] = { 0, 20, 4000, 8, 2345 };
		
		//int sum = 0;

		
		//int sum = Arrays.stream(data).sum();
		int sum = Arrays.stream(data).reduce(0, (currentSum, i) -> currentSum + i);

		IntStream.iterate(0, (currentInt) -> currentInt + 2)
			.limit(10).forEach(System.out::println);
	}
	
	
	public void foo( int count) throws FileNotFoundException {
		
		//for(int i = 0; i < count; i++) {
		IntStream.range(0, count).forEach(i -> {
		});
		
	}
	
	
}
