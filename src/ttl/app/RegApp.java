package ttl.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ttl.domain.Student;
import ttl.domain.Student.Status;
import ttl.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		RegApp ra = new RegApp();
		ra.go();
	}
	
	public void go() {
		StudentService service = new StudentService(); 

		List<Student> students = service.getAllStudents();
		
		Collections.sort(students);
		
		for(Student s : students) {
			System.out.println(s);
		}

		NameComparator nc = new NameComparator();
		Collections.sort(students, nc);
		
		System.out.println();
		for(Student s : students) {
			System.out.println(s);
		}
		
	}
	
	
	class NameComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			int r =  o1.getName().compareTo(o2.getName());
			if(r == 0) {
				r = o1.getId() - o2.getId();
			}
			return r;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static void mainEnum(String[] args) {
		
		StudentService service = new StudentService(); 
		
		String name = "Joe";
		String strStatus = "Part_time";
		
		Student s = new Student(name, Status.fromPrettyString(strStatus));
		
		System.out.println(s);
		
		Status [] statuses = Status.values();
		for(Status st : statuses) {
			System.out.println("code is " + st.getCode());
		}
	}
}
